package com.yglee.workshop.marbleroulette.api;

import com.yglee.workshop.marbleroulette.model.GameDTO;
import com.yglee.workshop.marbleroulette.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameApi {

    private final GameService gameService;

    @GetMapping
    public List<GameDTO> getGames() {
        return gameService.getGames();
    }
}
