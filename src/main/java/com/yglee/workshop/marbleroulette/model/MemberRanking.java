package com.yglee.workshop.marbleroulette.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRanking {
    private Integer rank;
    private String id;
    private String name;
    private String teamName;
    private Long score;
    private Long teamScore;
    private Long totalScore;
    private Double probability;

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }
}
