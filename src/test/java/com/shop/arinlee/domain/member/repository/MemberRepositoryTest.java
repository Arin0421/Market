package com.shop.arinlee.domain.member.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void MemberRepository확인(){
        assertThat(memberRepository).isNotNull();
    }
}