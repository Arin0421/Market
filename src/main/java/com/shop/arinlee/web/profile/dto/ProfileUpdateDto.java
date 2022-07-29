package com.shop.arinlee.web.profile.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
public class ProfileUpdateDto {

    private String name;

    private String address;

    @Builder
    private ProfileUpdateDto(String name, String address){
        this.name = name;
        this.address = address;
    }
}
