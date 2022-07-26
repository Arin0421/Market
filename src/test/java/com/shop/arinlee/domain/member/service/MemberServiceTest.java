package com.shop.arinlee.domain.member.service;

import com.shop.arinlee.domain.member.Entity.Member;
import com.shop.arinlee.domain.member.repository.MemberRepository;
import com.shop.arinlee.global.error.exception.BusinessException;
import com.shop.arinlee.domain.member.Entity.Role;
import com.shop.arinlee.domain.member.Entity.Type;
import com.shop.arinlee.global.error.exception.ErrorCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService target;

    @Mock
    private MemberRepository memberRepository;

    private final String email = "test@email.com";
    private final Member member = Member.builder()
            .email("test@email.com")
            .memberName("tester")
            .address("서울특별시")
            .password("password")
            .memberType(Type.BASE)
            .role(Role.ADMIN)
            .build();

    @Test
    public void 회원조회테스트_실패() throws Exception {
        //given
        doReturn(Optional.empty()).when(memberRepository).findByEmail(email);

        //when
        final BusinessException result = assertThrows(BusinessException.class, () -> target.findByEmail(email));

        //then
        assertThat(result.getMessage()).isEqualTo(ErrorCode.NO_MATCHING_MEMBER.getMessage());
    }

    @Test
    public void 회원조회테스트_성공() throws Exception {
        //given
        doReturn(Optional.of(member)).when(memberRepository).findByEmail(email);

        //when
        Member result = target.findByEmail(email);

        //then
        assertThat(result).isEqualTo(member);
    }
}