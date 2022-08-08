package com.shop.arinlee.domain.ItemImage.service;

import com.shop.arinlee.domain.ItemImage.entity.ItemImage;
import com.shop.arinlee.domain.ItemImage.repository.ItemImageRepository;
import com.shop.arinlee.domain.item.entity.Item;
import com.shop.arinlee.infra.image.FileService;
import com.shop.arinlee.infra.image.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemImageService {

    private final ItemImageRepository itemImageRepository;

    private final FileService fileService;
    private final String IMAGE_URL_PREFIX = "/images/";


    @Transactional
    public void saveItemImages(List<MultipartFile> itemImageFiles, Item item) throws IOException {
        for (int i = 0; i < 5; i++) {
            Boolean isRepImage = i == 0;
            saveItemImage(item, itemImageFiles.get(i), isRepImage);
        }
    }

    @Transactional
    public void saveItemImage(Item item, MultipartFile itemImageFile, Boolean isRepImage) throws IOException {

        UploadFile uploadFile = fileService.storeFile(itemImageFile);
        String storeFileName = uploadFile != null ? uploadFile.getStoreFileName() : "";
        String originalFilename = uploadFile != null ? uploadFile.getOriginalFileName() : "";
        String imageUrl = uploadFile != null ? IMAGE_URL_PREFIX + storeFileName : "";

        ItemImage itemImage = ItemImage.builder()
                .imageName(storeFileName)
                .imageUrl(imageUrl)
                .originalImageName(originalFilename)
                .isRepImage(isRepImage)
                .build();

        itemImage.setItem(item);
        itemImageRepository.save(itemImage);
    }

}