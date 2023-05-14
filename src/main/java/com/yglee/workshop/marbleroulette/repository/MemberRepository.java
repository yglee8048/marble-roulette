package com.yglee.workshop.marbleroulette.repository;

import com.yglee.workshop.marbleroulette.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
