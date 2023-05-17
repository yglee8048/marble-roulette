package com.yglee.workshop.marbleroulette.api;

import com.yglee.workshop.marbleroulette.model.OptionDTO;
import com.yglee.workshop.marbleroulette.model.TeamDTO;
import com.yglee.workshop.marbleroulette.model.TeamRanking;
import com.yglee.workshop.marbleroulette.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public void createTeam(@RequestBody TeamDTO teamDTO) {
        teamService.createTeam(teamDTO);
    }

    @PutMapping
    public void updateTeam(@RequestBody TeamDTO teamDTO) {
        teamService.updateTeam(teamDTO);
    }

    @DeleteMapping("/{teamName}")
    public void deleteTeam(@PathVariable("teamName") String teamName) {
        teamService.deleteTeam(teamName);
    }
}
