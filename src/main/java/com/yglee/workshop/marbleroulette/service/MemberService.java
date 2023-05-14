package com.yglee.workshop.marbleroulette.service;

import com.yglee.workshop.marbleroulette.domain.MemberScore;
import com.yglee.workshop.marbleroulette.domain.TeamScore;
import com.yglee.workshop.marbleroulette.model.MemberDTO;
import com.yglee.workshop.marbleroulette.model.MemberRanking;
import com.yglee.workshop.marbleroulette.model.OptionDTO;
import com.yglee.workshop.marbleroulette.repository.MemberRepository;
import com.yglee.workshop.marbleroulette.repository.query.QueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final QueryRepository queryRepository;

    public List<OptionDTO> getMemberOptions() {
        return memberRepository.findAll()
                .stream()
                .map(member -> member.getName() + "(" + member.getId() + ")")
                .map(OptionDTO::new)
                .collect(Collectors.toList());
    }

    public List<MemberDTO> getAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(member ->
                        new MemberDTO(
                                member.getId(),
                                member.getName(),
                                member.getTeam().getName()))
                .collect(Collectors.toList());
    }

    public List<MemberRanking> getMemberRankings() {
        Optional<Long> scoreSumOpt = queryRepository.getScoreSum();

        Map<String, TeamScore> teamScoreMap = queryRepository.getTeamScores()
                .stream()
                .collect(Collectors.toMap(TeamScore::getName, Function.identity()));

        List<MemberRanking> sortedRanking = queryRepository.getMemberScores()
                .stream()
                .map(memberScore -> map(memberScore, teamScoreMap))
                .sorted(Comparator.comparing(MemberRanking::getTotalScore).reversed())
                .collect(Collectors.toList());

        for (int i = 0; i < sortedRanking.size(); i++) {
            MemberRanking memberRanking = sortedRanking.get(i);
            memberRanking.setRank(i + 1);
            memberRanking.setProbability(scoreSumOpt
                    .map(scoreSum -> (double) (memberRanking.getTotalScore() / scoreSum))
                    .orElse(0D));
        }

        return sortedRanking;
    }

    private MemberRanking map(MemberScore memberScore, Map<String, TeamScore> teamScoreMap) {
        Long teamScore = Optional.ofNullable(teamScoreMap.get(memberScore.getTeamName()))
                .map(TeamScore::getScore)
                .orElse(0L);

        return new MemberRanking(
                null,
                memberScore.getId(),
                memberScore.getName(),
                memberScore.getTeamName(),
                memberScore.getScore(),
                teamScore,
                memberScore.getScore() + teamScore,
                null);
    }
}
