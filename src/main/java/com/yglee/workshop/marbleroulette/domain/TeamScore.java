package com.yglee.workshop.marbleroulette.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeamScore {
    private String name;
    private String leaderId;
    private Long score;
}
