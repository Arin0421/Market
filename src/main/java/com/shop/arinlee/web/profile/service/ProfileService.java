package com.shop.arinlee.web.profile.service;

import com.shop.arinlee.domain.member.Entity.Member;
import com.shop.arinlee.domain.member.service.MemberService;
import com.shop.arinlee.global.error.exception.BusinessException;
import com.shop.arinlee.global.error.exception.ErrorCode;
import com.shop.arinlee.web.profile.dto.ProfileUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ProfileService {
    private final MemberService memberService;


    public Member updateMemberInfo(String email) {
        Member result = memberService.findByEmail(email);
        if (result==null){
            throw new BusinessException(ErrorCode.NO_MATCHING_MEMBER);
        }
        return result;
    }

    @Transactional
    public Member updateMemberInfo(String email, ProfileUpdateDto profileUpdateDto){
        String memberName = profileUpdateDto.getName();
        String address = profileUpdateDto.getAddress();
        return memberService.update(email,memberName,address);
    }

    public Member getMemberInfo(String email) {
        if (memberService.findByEmail(email) == null){
            throw new BusinessException(ErrorCode.NO_MATCHING_MEMBER);
        }
        return memberService.findByEmail(email);
    }
}
