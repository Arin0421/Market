package com.shop.arinlee.web.itemdetail.controller;

import com.shop.arinlee.web.itemdetail.dto.RegisterOrderDto;
import com.shop.arinlee.web.itemdetail.service.ItemDtlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/itemdtl")
public class ItemDtlRegistController {
    private final ItemDtlService itemDtlService;

    @PostMapping("/order")
    public ResponseEntity registerOrderItem(@RequestBody RegisterOrderDto registerOrderDto, Principal principal) {
        itemDtlService.registerOrderItem(registerOrderDto, principal);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
