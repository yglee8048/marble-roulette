package com.yglee.workshop.marbleroulette.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeamRanking {
    private Integer rank;
    private String name;
    private String leaderId;
    private Integer score;

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
