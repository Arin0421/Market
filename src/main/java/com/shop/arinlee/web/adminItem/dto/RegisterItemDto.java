package com.shop.arinlee.web.adminItem.dto;

import com.shop.arinlee.domain.item.entity.Item;
import com.shop.arinlee.domain.item.entity.ItemSellStatus;
import com.shop.arinlee.domain.member.Entity.Member;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RegisterItemDto {

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemName;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    @NotEmpty(message = "상품 상세는 필수 입력 값입니다.")
    private String itemDetail;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    private List<MultipartFile> itemImageFiles;

    @Builder
    public RegisterItemDto(String itemName, Integer price, String itemDetail, Integer stockNumber, ItemSellStatus itemSellStatus, List<MultipartFile> itemImageFiles) {
        this.itemName = itemName;
        this.price = price;
        this.itemDetail = itemDetail;
        this.stockNumber = stockNumber;
        this.itemSellStatus = itemSellStatus;
        this.itemImageFiles = itemImageFiles;
    }

    public Item toItemEntity(Member member) {
        return Item.builder()
                .itemName(itemName)
                .price(price)
                .itemDetail(itemDetail)
                .stockNumber(stockNumber)
                .itemSellStatus(itemSellStatus)
                .member(member)
                .build();
    }
}