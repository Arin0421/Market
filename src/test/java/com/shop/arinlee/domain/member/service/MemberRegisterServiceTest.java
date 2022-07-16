package com.shop.arinlee.domain.member.service;

import com.shop.arinlee.domain.member.Entity.Member;
import com.shop.arinlee.global.error.exception.BusinessException;
import com.shop.arinlee.global.error.exception.ErrorCode;
import com.shop.arinlee.web.register.dto.RegisterFormDto;
import com.shop.arinlee.web.register.service.RegisterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberRegisterServiceTest {
    @InjectMocks
    private RegisterService target;
    @Mock
    private MemberService memberService;
    @Mock
    private PasswordEncoder passwordEncoder;

    private final String email = "test@email.com";
    private final String memberName = "test";
    private final String password = "password";
    private final String address = "서울";

    @Test
    public void 회원가입실패_비밀번호오류() throws Exception {
        //given
        RegisterFormDto tester = RegisterFormDto.builder()
                .email(email)
                .name(memberName)
                .address(address)
                .password(password)
                .password2("newPass")
                .build();
        //when
        final BusinessException result = assertThrows(BusinessException.class, ()->target.registerMember(tester));
        //then
        assertThat(result.getMessage()).isEqualTo(ErrorCode.MISMATCHED_PASSWORD.getMessage());
    }

    @Test
    public void 회원가입성공() throws Exception {
        //given
        RegisterFormDto registerDto = RegisterFormDto.builder()
                .email(email)
                .name(memberName)
                .address(address)
                .password(password)
                .password2(password)
                .build();
        //when
        target.registerMember(registerDto);
        //then
        verify(memberService, times(1)).savedMember(any(Member.class));
    }
}
