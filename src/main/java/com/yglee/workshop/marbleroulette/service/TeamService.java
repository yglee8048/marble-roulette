package com.yglee.workshop.marbleroulette.service;

import com.yglee.workshop.marbleroulette.entity.Team;
import com.yglee.workshop.marbleroulette.model.OptionDTO;
import com.yglee.workshop.marbleroulette.model.TeamDTO;
import com.yglee.workshop.marbleroulette.model.TeamRanking;
import com.yglee.workshop.marbleroulette.repository.TeamRepository;
import com.yglee.workshop.marbleroulette.repository.query.QueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final QueryRepository queryRepository;

    public List<OptionDTO> getTeamOptions() {
        return teamRepository.findAll()
                .stream()
                .map(team -> new OptionDTO(team.getName(), team.getLeaderId()))
                .collect(Collectors.toList());
    }

    public List<TeamDTO> getTeams() {
        return teamRepository.findAll()
                .stream()
                .map(team -> new TeamDTO(team.getName(), team.getLeaderId()))
                .collect(Collectors.toList());
    }

    public List<TeamRanking> getTeamRankings() {
        List<TeamRanking> sortedRanking = queryRepository.getTeamScores()
                .stream()
                .map(teamScore -> new TeamRanking(null,
                        teamScore.getName(),
                        teamScore.getLeaderId(),
                        teamScore.getScore()))
                .sorted(Comparator.comparing(TeamRanking::getScore).reversed())
                .collect(Collectors.toList());

        for (int i = 0; i < sortedRanking.size(); i++) {
            sortedRanking.get(i).setRank(i + 1);
        }

        return sortedRanking;
    }

    @Transactional
    public void createTeam(TeamDTO teamDTO) {
        teamRepository.save(new Team(teamDTO.getName(), teamDTO.getLeaderId()));
    }

    @Transactional
    public void updateTeam(TeamDTO teamDTO) {
        teamRepository.findById(teamDTO.getName())
                .map(team -> team.update(teamDTO.getLeaderId()));
    }
    
    @Transactional
    public void deleteTeam(String teamName) {
        teamRepository.deleteById(teamName);
    }
}
