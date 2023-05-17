package com.yglee.workshop.marbleroulette.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "game")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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

    public Game(String title, GameType type, String description, String image, Integer time, Long score) {
        this.title = title;
        this.type = type;
        this.description = description;
        this.image = image;
        this.time = time;
        this.score = score;
    }

    public Game update(String title, GameType type, String description, String image, Integer time, Long score) {
        this.title = title;
        this.type = type;
        this.description = description;
        this.image = image;
        this.time = time;
        this.score = score;
        return this;
    }
}
