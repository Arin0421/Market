package com.shop.arinlee.web.register.service;

import com.shop.arinlee.domain.member.Entity.Member;
import com.shop.arinlee.domain.member.service.MemberService;
import com.shop.arinlee.web.register.dto.RegisterFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterService {

    private final MemberService memberService;

    public void registerMember(RegisterFormDto registerFormDto){
        Member member = registerFormDto.toEntity();
        memberService.savedMember(member);
    }
}
