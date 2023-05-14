package com.yglee.workshop.marbleroulette.service;

import com.yglee.workshop.marbleroulette.domain.GameWinner;
import com.yglee.workshop.marbleroulette.model.GameDTO;
import com.yglee.workshop.marbleroulette.repository.query.QueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameService {

    private final QueryRepository queryRepository;

    public List<GameDTO> getGames() {
        return queryRepository.getGameWinners()
                .stream()
                .collect(Collectors.groupingBy(GameWinner::getTitle))
                .values()
                .stream()
                .map(winners -> {
                    GameWinner gameWinner = winners.get(0);

                    return new GameDTO(
                            gameWinner.getTitle(),
                            gameWinner.getType(),
                            gameWinner.getDescription(),
                            gameWinner.getImage(),
                            gameWinner.getTime(),
                            gameWinner.getScore(),
                            winners.stream()
                                    .map(GameWinner::getWinnerName)
                                    .filter(Objects::nonNull)
                                    .collect(Collectors.toList()));
                })
                .collect(Collectors.toList());
    }
}
