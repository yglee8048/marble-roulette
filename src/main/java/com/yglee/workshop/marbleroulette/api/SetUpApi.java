package com.yglee.workshop.marbleroulette.api;

import com.yglee.workshop.marbleroulette.model.GameDTO;
import com.yglee.workshop.marbleroulette.model.MemberDTO;
import com.yglee.workshop.marbleroulette.model.TeamDTO;
import com.yglee.workshop.marbleroulette.service.CommandService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/set-up")
@RequiredArgsConstructor
public class SetUpApi {

    private final CommandService commandService;

    @PostMapping("/members")
    public void setUpMembers(@RequestBody MemberSetUp data) {
        commandService.resetMembers(data);
    }

    @PostMapping("/games")
    public void setUpGames(@RequestBody List<GameDTO> games) {
        commandService.resetGames(games);
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberSetUp {
        private List<MemberDTO> members;
        private List<TeamDTO> teams;
    }
}
