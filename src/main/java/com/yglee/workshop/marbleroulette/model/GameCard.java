package com.yglee.workshop.marbleroulette.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GameCard {
    private GameDTO game;
    private List<String> winners;
}
