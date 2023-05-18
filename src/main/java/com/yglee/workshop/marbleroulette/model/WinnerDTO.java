package com.yglee.workshop.marbleroulette.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WinnerDTO {
    private Integer score;
    private List<String> winners;
}
