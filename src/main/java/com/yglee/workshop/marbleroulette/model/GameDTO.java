package com.yglee.workshop.marbleroulette.model;

import com.yglee.workshop.marbleroulette.entity.GameType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GameDTO {
    private Long id;
    private String title;
    private GameType type;
    private String description;
    private String image;
    private Integer time;
    private Long score;
    private List<String> winners;
}
