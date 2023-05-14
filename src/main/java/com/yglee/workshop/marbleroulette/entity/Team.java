package com.yglee.workshop.marbleroulette.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "team")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Team {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "leader_id")
    private String leaderId;
}
