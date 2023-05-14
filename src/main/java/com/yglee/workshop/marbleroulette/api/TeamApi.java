package com.yglee.workshop.marbleroulette.api;

import com.yglee.workshop.marbleroulette.model.OptionDTO;
import com.yglee.workshop.marbleroulette.model.TeamDTO;
import com.yglee.workshop.marbleroulette.model.TeamRanking;
import com.yglee.workshop.marbleroulette.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamApi {

    private final TeamService teamService;

    @GetMapping
    public List<TeamDTO> getAllTeams() {
        return teamService.getTeams();
    }

    @GetMapping("/ranking")
    public List<TeamRanking> getTeamRankings() {
        return teamService.getTeamRankings();
    }

    @GetMapping("/options")
    public List<OptionDTO> getTeamOptions() {
        return teamService.getTeamOptions();
    }
}
