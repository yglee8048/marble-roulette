package com.yglee.workshop.marbleroulette.repository;

import com.yglee.workshop.marbleroulette.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
