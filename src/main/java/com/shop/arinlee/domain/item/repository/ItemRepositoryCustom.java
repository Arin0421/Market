package com.shop.arinlee.domain.item.repository;

import com.shop.arinlee.web.main.dto.ItemSearchDto;
import com.shop.arinlee.web.main.dto.MainItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}
