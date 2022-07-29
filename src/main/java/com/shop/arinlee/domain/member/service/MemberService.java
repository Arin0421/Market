package com.shop.arinlee.domain.member.service;

import com.shop.arinlee.domain.member.Entity.Member;
import com.shop.arinlee.domain.member.repository.MemberRepository;
import com.shop.arinlee.global.config.UserDetailsImpl;
import com.shop.arinlee.global.error.exception.BusinessException;
import com.shop.arinlee.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member savedMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> optionalMember = memberRepository.findByEmail(member.getEmail());
        if (optionalMember.isPresent()) {
            throw new BusinessException(ErrorCode.ALREADY_REGISTERED_MEMBER);
        }
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(ErrorCode.NO_MATCHING_MEMBER));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Member member = findByEmail(email);

        if (member == null){
            throw new UsernameNotFoundException(email);
        }

        return UserDetailsImpl.create(member);
    }

    @Transactional
    public Member update(String email, String memberName, String address) {
        Member member = findByEmail(email);
        if (member==null){
            throw new BusinessException(ErrorCode.NO_MATCHING_MEMBER);
        }
        member.updateInfo(memberName, address);

        return member;
    }
}
