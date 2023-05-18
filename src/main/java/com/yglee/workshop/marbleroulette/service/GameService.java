package com.yglee.workshop.marbleroulette.service;

import com.yglee.workshop.marbleroulette.domain.GameWinner;
import com.yglee.workshop.marbleroulette.entity.*;
import com.yglee.workshop.marbleroulette.model.GameDTO;
import com.yglee.workshop.marbleroulette.model.OptionDTO;
import com.yglee.workshop.marbleroulette.model.WinnerDTO;
import com.yglee.workshop.marbleroulette.repository.GameRepository;
import com.yglee.workshop.marbleroulette.repository.MemberRepository;
import com.yglee.workshop.marbleroulette.repository.TeamRepository;
import com.yglee.workshop.marbleroulette.repository.WinnerRepository;
import com.yglee.workshop.marbleroulette.repository.query.QueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
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
                .collect(Collectors.groupingBy(GameWinner::getGameId))
                .values()
                .stream()
                .map(winners -> {
                    GameWinner game = winners.get(0);

                    List<WinnerDTO> winnerDTOS = winners.stream()
                            .filter(e -> e.getScore() != null)
                            .collect(Collectors.groupingBy(GameWinner::getScore))
                            .entrySet()
                            .stream()
                            .map(entry -> {
                                List<OptionDTO> options = entry.getValue()
                                        .stream()
                                        .map(gameWinner -> new OptionDTO(gameWinner.getWinnerName(), gameWinner.getDetail()))
                                        .collect(Collectors.toList());

                                return new WinnerDTO(
                                        UUID.randomUUID().toString(),
                                        entry.getKey(),
                                        options
                                );
                            })
                            .sorted(Comparator.comparing(WinnerDTO::getScore))
                            .collect(Collectors.toList());

                    return new GameDTO(
                            game.getGameId(),
                            game.getTitle(),
                            game.getType(),
                            game.getDescription(),
                            game.getImage(),
                            game.getTime(),
                            winnerDTOS
                    );
                })
                .collect(Collectors.toList());
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
                .forEach(winnerDTO ->
                        winnerDTO.getNames()
                                .forEach(winner -> {
                                    if (game.getType() == GameType.PERSONAL) {
                                        Member member = memberRepository.findById(winner.getName()).orElseThrow();
                                        winnerRepository.save(new Winner(game, winnerDTO.getScore(), member));
                                    } else {
                                        Team team = teamRepository.findById(winner.getName()).orElseThrow();
                                        winnerRepository.save(new Winner(game, winnerDTO.getScore(), team));
                                    }
                                }));
    }

    @Transactional
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }
}
