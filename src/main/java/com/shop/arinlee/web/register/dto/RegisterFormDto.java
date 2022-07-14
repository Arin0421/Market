package com.shop.arinlee.web.register.dto;

import com.shop.arinlee.domain.member.Entity.Member;
import com.shop.arinlee.domain.member.Entity.Role;
import com.shop.arinlee.domain.member.Entity.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterFormDto {
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 8,max=16,message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
    private String password;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    private String password2;

    @NotEmpty(message = "주소는 필수 입력 값입니다.")
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
