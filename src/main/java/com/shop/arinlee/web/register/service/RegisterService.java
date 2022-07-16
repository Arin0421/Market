package com.shop.arinlee.web.register.service;

import com.shop.arinlee.domain.member.Entity.Member;
import com.shop.arinlee.domain.member.service.MemberService;
import com.shop.arinlee.global.error.exception.BusinessException;
import com.shop.arinlee.global.error.exception.ErrorCode;
import com.shop.arinlee.web.register.dto.RegisterFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterService {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    public void registerMember(RegisterFormDto registerFormDto){
        validatePasswordCheck(registerFormDto);
        Member member = registerFormDto.toEntity(passwordEncoder);
        memberService.savedMember(member);
    }

    private void validatePasswordCheck(RegisterFormDto registerDto) {
        String password1 = registerDto.getPassword();
        String password2 = registerDto.getPassword2();
        if (!password1.equals(password2)) {
            throw new BusinessException(ErrorCode.MISMATCHED_PASSWORD);
        }
    }
}
