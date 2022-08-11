package com.shop.arinlee.web.adminItem.service;

import com.shop.arinlee.domain.ItemImage.entity.ItemImage;
import com.shop.arinlee.domain.ItemImage.service.ItemImageService;
import com.shop.arinlee.domain.item.entity.Item;
import com.shop.arinlee.domain.item.service.ItemService;
import com.shop.arinlee.domain.member.Entity.Member;
import com.shop.arinlee.domain.member.service.MemberService;
import com.shop.arinlee.web.adminItem.dto.RegisterItemDto;
import com.shop.arinlee.web.adminItem.dto.UpdateItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminItemService {

    private final ItemService itemService;
    private final ItemImageService itemImageService;
    private final MemberService memberService;

    @Transactional
    public Long addNewAdminItem(RegisterItemDto adminItemDto, Principal principal) throws IOException {
        Member member = findMemberByPrincipal(principal);
        Item item = adminItemDto.toItemEntity(member);
        itemService.saveItem(item);

        itemImageService.saveItemImages(adminItemDto.getItemImageFiles(), item);
        return item.getId();
    }

    private Member findMemberByPrincipal(Principal principal) {
        String email = principal.getName();
        Member member = memberService.findByEmail(email);
                //.orElseThrow(() -> new BusinessException(ErrorCode.NO_MATCHING_MEMBER));
        return member;
    }

    public UpdateItemDto getItemAndImages(long itemId) {
        Item item = itemService.findItemById(itemId);
        List<UpdateItemDto.ItemImageDto> itemImageDtos = getItemImageDtos(item);

        return UpdateItemDto.of(item, itemImageDtos);
    }

    private List<UpdateItemDto.ItemImageDto> getItemImageDtos(Item item) {
        List<ItemImage> imagesList = itemImageService.findImagesByItem(item);
        return imagesList.stream().map(
                image ->
                        UpdateItemDto.ItemImageDto.builder()
                                .itemImageId(image.getId())
                                .originalImageName(image.getOriginalImageName())
                                .build()
        ).collect(Collectors.toList());
    }

    @Transactional
    public void updateItem(UpdateItemDto updateItemDto) throws IOException {
            Item updateItem = itemService.updateItem(updateItemDto.getItemId(), updateItemDto.toItemEntity());
        updateItemImages(updateItem, updateItemDto);
    }

    private void updateItemImages(Item item, UpdateItemDto updateItemDto) throws IOException {
        List<ItemImage> itemImages = itemImageService.findImagesByItem(item);
        List<String> originalImageNames = updateItemDto.getOriginalImageNames();
        List<MultipartFile> itemImageFiles = updateItemDto.getItemImageFiles();

        for (int i = 0; i < itemImages.size(); i++) {
            ItemImage itemImage = itemImages.get(i);
            String imageName = originalImageNames.get(i);
            MultipartFile imageFile = itemImageFiles.get(i);

            if (!imageFile.isEmpty()) {
                itemImageService.updateItemImage(itemImage, imageFile);
            } else if(imageName.isEmpty() && !itemImage.getOriginalImageName().isEmpty()){
                itemImageService.deleteItemImage(itemImage);
            }

        }

    }
}