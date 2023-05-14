package com.yglee.workshop.marbleroulette.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberScore {
    private String id;
    private String name;
    private String teamName;
    private Long score;
}
