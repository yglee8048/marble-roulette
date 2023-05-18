package com.yglee.workshop.marbleroulette.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "winner")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Winner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_title")
    private Game game;

    @Column(name = "score")
    private Integer score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_name")
    private Team team;

    public Winner(Game game, Integer score, Member member) {
        this.game = game;
        this.score = score;
        this.member = member;
    }

    public Winner(Game game, Integer score, Team team) {
        this.game = game;
        this.score = score;
        this.team = team;
    }
}
