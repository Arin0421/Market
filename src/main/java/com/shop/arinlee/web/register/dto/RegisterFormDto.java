package com.shop.arinlee.web.register.dto;

import com.shop.arinlee.domain.member.Entity.Member;
import com.shop.arinlee.domain.member.Entity.Role;
import com.shop.arinlee.domain.member.Entity.Type;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
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

    public Member toEntity(PasswordEncoder passwordEncoder){
        return Member.builder()
                .memberName(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .address(address)
                .memberType(Type.BASE)
                .role(Role.ADMIN)
                .build();
    }

    @Builder
    public RegisterFormDto(String name, String address, String email, String password, String password2) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.password2 = password2;
    }


}
