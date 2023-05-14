package com.yglee.workshop.marbleroulette.service;

import com.yglee.workshop.marbleroulette.api.SetUpApi;
import com.yglee.workshop.marbleroulette.entity.Game;
import com.yglee.workshop.marbleroulette.entity.Member;
import com.yglee.workshop.marbleroulette.entity.Team;
import com.yglee.workshop.marbleroulette.model.GameDTO;
import com.yglee.workshop.marbleroulette.model.MemberDTO;
import com.yglee.workshop.marbleroulette.model.TeamDTO;
import com.yglee.workshop.marbleroulette.repository.GameRepository;
import com.yglee.workshop.marbleroulette.repository.MemberRepository;
import com.yglee.workshop.marbleroulette.repository.TeamRepository;
import com.yglee.workshop.marbleroulette.repository.WinnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommandService {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final GameRepository gameRepository;
    private final WinnerRepository winnerRepository;

    public void resetMembers(SetUpApi.MemberSetUp data) {
        winnerRepository.deleteAll();
        memberRepository.deleteAll();
        teamRepository.deleteAll();

        resetTeams(data.getTeams());
        resetMembers(data.getMembers());
    }

    private void resetTeams(List<TeamDTO> teams) {
        teams.forEach(team -> teamRepository.save(new Team(team.getName(), team.getLeaderId())));
    }

    private void resetMembers(List<MemberDTO> members) {
        members.forEach(member -> {
            Team team = teamRepository.findById(member.getTeamName()).orElseThrow();
            memberRepository.save(new Member(member.getId(), member.getName(), team));
        });
    }

    public void resetGames(List<GameDTO> games) {
        winnerRepository.deleteAll();
        gameRepository.deleteAll();

        games.forEach(game ->
                gameRepository.save(
                        new Game(game.getTitle(),
                                game.getType(),
                                game.getDescription(),
                                game.getImage(),
                                game.getTime(),
                                game.getScore())));
    }
}
