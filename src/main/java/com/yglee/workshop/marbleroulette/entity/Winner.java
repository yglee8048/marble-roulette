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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_name")
    private Team team;

    public Winner(Game game, Member member) {
        this.game = game;
        this.member = member;
    }

    public Winner(Game game, Team team) {
        this.game = game;
        this.team = team;
    }
}
