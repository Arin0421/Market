package com.shop.arinlee.domain.member;


import com.shop.arinlee.domain.member.Entity.Member;
import com.shop.arinlee.web.register.dto.RegisterFormDto;
import com.shop.arinlee.web.register.service.RegisterService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


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
        RegisterFormDto member = new RegisterFormDto(name,address,email,password,password2);

        //when
        registerService.registerMember(member);

        //then
        assertThat(member.getEmail()).isEqualTo("test@email.com");
        assertThat(member.getAddress()).isEqualTo("부천");
        assertThat(member.getName()).isEqualTo("test");
        assertThat(member.getPassword()).isEqualTo("1234");

    }

    @Test
    @DisplayName("중복 회원 가입 테스트")
    public void 중복회원가입테스트(){
        //given
        RegisterFormDto member1 = new RegisterFormDto("test","test@email.com","1234","1234","서울");
        RegisterFormDto member2 = new RegisterFormDto("test","test@email.com","1234","1234","서울");

        //when
        registerService.registerMember(member1);


        //then
        Throwable e = assertThrows(IllegalStateException.class,()->{
            registerService.registerMember(member2);});

        assertEquals("이미 가입된 회원입니다.",e.getMessage());
    }


}
