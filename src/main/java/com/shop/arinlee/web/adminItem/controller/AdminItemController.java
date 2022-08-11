package com.shop.arinlee.web.adminItem.controller;

import com.shop.arinlee.global.error.exception.ErrorCode;
import com.shop.arinlee.web.adminItem.dto.RegisterItemDto;
import com.shop.arinlee.web.adminItem.dto.UpdateItemDto;
import com.shop.arinlee.web.adminItem.service.AdminItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/items")
public class AdminItemController {

    private final AdminItemService adminItemService;

    @GetMapping("/new")
    public String getAdminItemView(Model model) {
        model.addAttribute("adminItemDto", new RegisterItemDto());
        return "adminitem/registeritemform";
    }

    @PostMapping(value = "/new")
    public String itemNew(
            Principal principal,
            @Valid @ModelAttribute RegisterItemDto adminItemDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        Long itemId;

        if (adminItemDto.getItemImageFiles().get(0).isEmpty()) {
            bindingResult.reject("errorMessage", ErrorCode.NO_REP_IMAGE.getMessage());
            return "adminitem/registeritemform";
        } else if (bindingResult.hasErrors()) {
            return "adminitem/registeritemform";
        }

        try {
            itemId = adminItemService.addNewAdminItem(adminItemDto, principal);
            redirectAttributes.addAttribute(("itemId"), itemId);
        } catch (Exception e) {
            bindingResult.reject("errorMessage", ErrorCode.ADD_ITEM_ERROR.getMessage());
            return "adminitem/registeritemform";
        }

        return "redirect:/admin/items/{itemId}";
    }

    @GetMapping("/{itemId}")
    public String itemEdit(
            @PathVariable Long itemId,
            Model model
    ) {
        UpdateItemDto updateItemDto = adminItemService.getItemAndImages(itemId);
        model.addAttribute("updateItemDto", updateItemDto);
        return "adminitem/updateitemform";
    }
}