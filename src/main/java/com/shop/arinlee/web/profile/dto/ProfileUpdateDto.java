package com.shop.arinlee.web.profile.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class ProfileUpdateDto {

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "주소는 필수 입력 값입니다.")
    private String address;

    @Builder
    private ProfileUpdateDto(String name, String address){
        this.name = name;
        this.address = address;
    }
}
