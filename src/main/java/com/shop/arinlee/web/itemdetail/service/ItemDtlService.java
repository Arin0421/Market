package com.shop.arinlee.web.itemdetail.service;

import com.shop.arinlee.domain.item.entity.Item;
import com.shop.arinlee.domain.item.service.ItemService;
import com.shop.arinlee.web.itemdetail.dto.ItemDtlDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemDtlService {

    private final ItemService itemService;

    public ItemDtlDto getItemDtl(Long itemId) {

        Item item = itemService.findItemById(itemId);

        return ItemDtlDto.of(item);
    }
}