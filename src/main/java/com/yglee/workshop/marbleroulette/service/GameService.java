package com.yglee.workshop.marbleroulette.service;

import com.yglee.workshop.marbleroulette.domain.GameWinner;
import com.yglee.workshop.marbleroulette.entity.*;
import com.yglee.workshop.marbleroulette.model.GameDTO;
import com.yglee.workshop.marbleroulette.repository.GameRepository;
import com.yglee.workshop.marbleroulette.repository.MemberRepository;
import com.yglee.workshop.marbleroulette.repository.TeamRepository;
import com.yglee.workshop.marbleroulette.repository.WinnerRepository;
import com.yglee.workshop.marbleroulette.repository.query.QueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GameService {

    private final QueryRepository queryRepository;
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final GameRepository gameRepository;
    private final WinnerRepository winnerRepository;

    public List<GameDTO> getGames() {
        return queryRepository.getGameWinners()
                .stream()
                .collect(Collectors.groupingBy(GameWinner::getId))
                .values()
                .stream()
                .map(winners -> {
                    GameWinner gameWinner = winners.get(0);

                    return new GameDTO(
                            gameWinner.getId(),
                            gameWinner.getTitle(),
                            gameWinner.getType(),
                            gameWinner.getDescription(),
                            gameWinner.getImage(),
                            gameWinner.getTime(),
                            getWinnerMap(winners)
                    );
                })
                .collect(Collectors.toList());
    }

    private Map<Integer, List<String>> getWinnerMap(List<GameWinner> winners) {
        Map<Integer, List<String>> winnerMap = new HashMap<>();
        for (GameWinner winner : winners) {
            if (winnerMap.containsKey(winner.getScore())) {
                winnerMap.get(winner.getScore()).add(winner.getWinnerName());
            } else {
                List<String> winnerNames = new ArrayList<>();
                winnerNames.add(winner.getWinnerName());
                winnerMap.put(winner.getScore(), winnerNames);
            }
        }
        return winnerMap;
    }

    @Transactional
    public void addGame(GameDTO gameDTO) {
        gameRepository.save(
                new Game(
                        gameDTO.getTitle(),
                        gameDTO.getType(),
                        gameDTO.getDescription(),
                        gameDTO.getImage(),
                        gameDTO.getTime()));
    }

    @Transactional
    public void updateGame(GameDTO gameDTO) {
        Game game = gameRepository.findById(gameDTO.getId())
                .map(found -> found.update(
                        gameDTO.getTitle(),
                        gameDTO.getType(),
                        gameDTO.getDescription(),
                        gameDTO.getImage(),
                        gameDTO.getTime()))
                .orElseThrow();

        winnerRepository.deleteAllByGame(game);
        gameDTO.getWinners()
                .forEach((score, winnerNames) ->
                        winnerNames
                                .forEach(winner -> {
                                    if (game.getType() == GameType.PERSONAL) {
                                        Member member = memberRepository.findById(winner).orElseThrow();
                                        winnerRepository.save(new Winner(game, score, member));
                                    } else {
                                        Team team = teamRepository.findById(winner).orElseThrow();
                                        winnerRepository.save(new Winner(game, score, team));
                                    }
                                }));
    }

    @Transactional
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }
}
