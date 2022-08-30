package com.shop.arinlee.web.itemdetail.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class RegisterOrderDto {

    private Long itemId;
    private int count;
}
