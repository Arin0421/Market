package com.shop.arinlee.domain.item.service;

import com.shop.arinlee.domain.item.entity.Item;
import com.shop.arinlee.domain.item.repository.ItemRepository;
import com.shop.arinlee.global.error.exception.BusinessException;
import com.shop.arinlee.global.error.exception.ErrorCode;
import com.shop.arinlee.global.error.exception.StockException;
import com.shop.arinlee.web.main.dto.ItemSearchDto;
import com.shop.arinlee.web.main.dto.MainItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public Item findItemById(Long itemId) {

        return itemRepository.findById(itemId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NO_MATCHING_ITEM));
    }

    @Transactional
    public Item updateItem(Long itemId, Item updateItem){
        Item item = findItemById(itemId);
        item.update(updateItem);
        return item;
    }

    @Transactional(readOnly = true)
    public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable){
        return itemRepository.getMainItemPage(itemSearchDto, pageable);
    }

    @Transactional
    public void reduceStock(Item item, int amount) {
        int stock = item.getStockNumber();
        if (stock < amount) {
            throw new StockException(item.getStockNumber());
        }

        item.reduceStock(amount);
    }
}
