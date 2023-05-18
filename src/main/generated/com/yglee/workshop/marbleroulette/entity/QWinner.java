package com.yglee.workshop.marbleroulette.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWinner is a Querydsl query type for Winner
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWinner extends EntityPathBase<Winner> {

    private static final long serialVersionUID = 584260407L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWinner winner = new QWinner("winner");

    public final QGame game;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final NumberPath<Integer> score = createNumber("score", Integer.class);

    public final QTeam team;

    public QWinner(String variable) {
        this(Winner.class, forVariable(variable), INITS);
    }

    public QWinner(Path<? extends Winner> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWinner(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWinner(PathMetadata metadata, PathInits inits) {
        this(Winner.class, metadata, inits);
    }

    public QWinner(Class<? extends Winner> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.game = inits.isInitialized("game") ? new QGame(forProperty("game")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
        this.team = inits.isInitialized("team") ? new QTeam(forProperty("team")) : null;
    }

}

