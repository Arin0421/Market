package com.shop.arinlee.domain.member;


import com.shop.arinlee.domain.member.Entity.Member;
import com.shop.arinlee.web.register.dto.RegisterFormDto;
import com.shop.arinlee.web.register.service.RegisterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
public class MemberRegisterTest {

    @Autowired
    private RegisterService registerService;

    @Test
    public void 회원가입테스트(){
        //given
        String name = "test";
        String email = "test@email.com";
        String password = "1234";
        String password2 = "1234";
        String address = "부천";
        RegisterFormDto member = new RegisterFormDto(name,email,password,password2,address);

        //when
        registerService.registerMember(member);

        //then
        assertThat(member.getEmail()).isEqualTo("test@email.com");
        assertThat(member.getAddress()).isEqualTo("부천");
        assertThat(member.getName()).isEqualTo("test");
        assertThat(member.getPassword()).isEqualTo("1234");

    }


}
