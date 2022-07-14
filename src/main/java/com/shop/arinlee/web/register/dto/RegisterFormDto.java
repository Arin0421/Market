package com.shop.arinlee.web.register.dto;

import com.shop.arinlee.domain.member.Entity.Member;
import com.shop.arinlee.domain.member.Entity.Role;
import com.shop.arinlee.domain.member.Entity.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterFormDto {
    private String name;

    private String email;

    private String password;

    private String password2;

    private String address;

    public Member toEntity(){
        return Member.builder()
                .memberName(name)
                .email(email)
                .password(password)
                .address(address)
                .memberType(Type.BASE)
                .role(Role.ADMIN)
                .build();
    }


}
