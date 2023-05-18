package com.yglee.workshop.marbleroulette.domain;

import com.yglee.workshop.marbleroulette.entity.GameType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GameWinner {
    private Long gameId;
    private String title;
    private GameType type;
    private String description;
    private String image;
    private Integer time;
    private Integer score;
    private String memberId;
    private String teamName;
    private String detail;

    public String getWinnerName() {
        return memberId != null ? memberId : teamName;
    }
}
