package com.shop.arinlee.web.itemdetail.controller;

import com.shop.arinlee.web.itemdetail.dto.ItemDtlDto;
import com.shop.arinlee.web.itemdetail.service.ItemDtlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ItemDetailController {

    private final ItemDtlService itemDtlService;

    @GetMapping(value = "/itemdtl/{itemId}")
    public String itemDtl(Model model, @PathVariable("itemId") Long itemId){
        ItemDtlDto item = itemDtlService.getItemDtl(itemId);
        model.addAttribute("item",item);

        return "item/itemdtl";
    }
}
