package com.yglee.workshop.marbleroulette.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "game")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Game {

    @Id
    @Column(name = "title")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private GameType type;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "time")
    private Integer time;

    @Column(name = "score")
    private Long score;
}
