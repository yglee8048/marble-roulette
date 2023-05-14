package com.yglee.workshop.marbleroulette.repository.query;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yglee.workshop.marbleroulette.domain.GameWinner;
import com.yglee.workshop.marbleroulette.domain.MemberScore;
import com.yglee.workshop.marbleroulette.domain.TeamScore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.yglee.workshop.marbleroulette.entity.QGame.game;
import static com.yglee.workshop.marbleroulette.entity.QMember.member;
import static com.yglee.workshop.marbleroulette.entity.QTeam.team;
import static com.yglee.workshop.marbleroulette.entity.QWinner.winner;

@Repository
@RequiredArgsConstructor
public class QueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<MemberScore> getMemberScores() {
        return queryFactory
                .from(member)
                .leftJoin(winner).on(winner.member.eq(member))
                .leftJoin(game).on(winner.game.eq(game))
                .groupBy(member)
                .select(Projections.fields(MemberScore.class,
                        member.id,
                        member.name,
                        member.team.name.as("teamName"),
                        game.score.sum().coalesce(0L).as("score")
                ))
                .fetch();
    }

    public List<TeamScore> getTeamScores() {
        return queryFactory
                .from(team)
                .leftJoin(winner).on(winner.team.eq(team))
                .leftJoin(game).on(winner.game.eq(game))
                .groupBy(team)
                .select(Projections.fields(TeamScore.class,
                        team.name,
                        team.leaderId,
                        game.score.sum().coalesce(0L).as("score")
                ))
                .fetch();
    }

    public Optional<Long> getScoreSum() {
        return Optional.ofNullable(queryFactory
                .from(winner)
                .join(game).on(winner.game.eq(game))
                .select(game.score.sum())
                .fetchOne());
    }

    public List<GameWinner> getGameWinners() {
        return queryFactory
                .from(game)
                .leftJoin(winner).on(winner.game.eq(game))
                .leftJoin(member).on(winner.member.eq(member))
                .leftJoin(team).on(winner.team.eq(team))
                .select(Projections.fields(GameWinner.class,
                        game.title,
                        game.type,
                        game.description,
                        game.image,
                        game.time,
                        game.score,
                        member.name,
                        team.name
                ))
                .fetch();
    }
}
