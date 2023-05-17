package com.yglee.workshop.marbleroulette.api;

import com.yglee.workshop.marbleroulette.model.GameDTO;
import com.yglee.workshop.marbleroulette.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public void createGame(@RequestBody GameDTO gameDTO) {
        gameService.addGame(gameDTO);
    }

    @PutMapping
    public void updateGame(@RequestBody GameDTO gameDTO) {
        gameService.updateGame(gameDTO);
    }

    @DeleteMapping("/{gameId}")
    public void deleteGame(@PathVariable("gameId") Long gameId) {
        gameService.deleteGame(gameId);
    }
}
