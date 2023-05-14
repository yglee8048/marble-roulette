package com.yglee.workshop.marbleroulette.domain;

import com.yglee.workshop.marbleroulette.entity.GameType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GameWinner {
    private String title;
    private GameType type;
    private String description;
    private String image;
    private Integer time;
    private Long score;
    private String memberName;
    private String teamName;

    public String getWinnerName() {
        return memberName != null ? memberName : teamName;
    }
}
