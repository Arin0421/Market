package com.shop.arinlee.domain.member.repository;

import com.shop.arinlee.domain.member.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByEmail(String email);
}
