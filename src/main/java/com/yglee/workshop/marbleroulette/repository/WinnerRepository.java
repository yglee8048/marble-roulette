package com.yglee.workshop.marbleroulette.repository;

import com.yglee.workshop.marbleroulette.entity.Winner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WinnerRepository extends JpaRepository<Winner, Long> {
}
