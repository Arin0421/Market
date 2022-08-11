package com.shop.arinlee.web.adminItem.dto;

import com.shop.arinlee.domain.item.entity.Item;
import com.shop.arinlee.domain.item.entity.ItemSellStatus;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UpdateItemDto {
    private Long itemId;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemName;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    @NotBlank(message = "상품 상세는 필수 입력 값입니다.")
    private String itemDetail;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    private List<MultipartFile> itemImageFiles;

    private List<ItemImageDto> itemImageDtos;

    private List<String> originalImageNames;

    @Builder
    @Getter @Setter
    public static class ItemImageDto {
        private Long itemImageId;
        private String originalImageName;
    }

    @Builder
    public UpdateItemDto(Long itemId, String itemName, Integer price, String itemDetail, Integer stockNumber, ItemSellStatus itemSellStatus, List<MultipartFile> itemImageFiles, List<ItemImageDto> itemImageDtos, List<String> originalImageNames) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.itemDetail = itemDetail;
        this.stockNumber = stockNumber;
        this.itemSellStatus = itemSellStatus;
        this.itemImageFiles = itemImageFiles;
        this.itemImageDtos = itemImageDtos;
        this.originalImageNames = originalImageNames;
    }

    public static UpdateItemDto of(Item item, List<ItemImageDto> itemImageDtos) {
        return UpdateItemDto.builder()
                .itemId(item.getId())
                .itemName(item.getItemName())
                .price(item.getPrice())
                .itemDetail(item.getItemDetail())
                .stockNumber(item.getStockNumber())
                .itemSellStatus(item.getItemSellStatus())
                .itemImageDtos(itemImageDtos)
                .build();
    }

    public Item toItemEntity() {
        return Item.builder()
                .itemName(itemName)
                .price(price)
                .itemDetail(itemDetail)
                .stockNumber(stockNumber)
                .itemSellStatus(itemSellStatus)
                .build();
    }
}
