package com.yglee.workshop.marbleroulette.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private String id;
    private String name;
    private String teamName;
}
