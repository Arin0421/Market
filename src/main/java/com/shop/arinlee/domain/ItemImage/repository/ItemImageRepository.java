package com.shop.arinlee.domain.ItemImage.repository;

import com.shop.arinlee.domain.ItemImage.entity.ItemImage;
import com.shop.arinlee.domain.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemImageRepository extends JpaRepository<ItemImage,Long> {
    List<ItemImage> findByItemOrderById(Item item);
}
