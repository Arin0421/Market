package com.shop.arinlee.web.profile;

import com.shop.arinlee.domain.member.Entity.Member;
import com.shop.arinlee.domain.member.Entity.Role;
import com.shop.arinlee.domain.member.Entity.Type;
import com.shop.arinlee.domain.member.service.MemberService;
import com.shop.arinlee.global.error.exception.BusinessException;
import com.shop.arinlee.global.error.exception.ErrorCode;
import com.shop.arinlee.web.profile.dto.ProfileUpdateDto;
import com.shop.arinlee.web.profile.service.ProfileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
public class ProfileServiceTest {

    @InjectMocks
    private ProfileService target;

    @Mock
    private MemberService memberService;

    private final String email = "test@email.com";

    @Test
    public void 회원정보수정실패() throws Exception{
        //given
        doThrow(new BusinessException(ErrorCode.NO_MATCHING_MEMBER))
                .when(memberService).update(email,"name","address");

        //when
        final BusinessException result = assertThrows(BusinessException.class,()->
                target.updateMemberInfo(email, ProfileUpdateDto.builder()
                        .name("name")
                        .address("address")
                        .build()
                )
        );

        //then
        assertThat(result.getMessage()).isEqualTo(ErrorCode.NO_MATCHING_MEMBER.getMessage());
    }

    @Test
    public void 회원정보수정성공() throws Exception{
        //given
        final Member member = Member.builder()
                .email("test@email.com")
                .memberName("name")
                .address("address")
                .password("password")
                .memberType(Type.BASE)
                .role(Role.USER)
                .build();

        doReturn(member).when(memberService).update(email,"name","address");

        //when
        Member result = target.updateMemberInfo(email,ProfileUpdateDto.builder()
                .address("address")
                .name("name")
                .build());

        //then
        assertThat(result.getMemberName()).isEqualTo("name");
        assertThat(result.getAddress()).isEqualTo("address");
    }
}