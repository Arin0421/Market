package com.shop.arinlee.web.itemdetail.service;

import com.shop.arinlee.domain.item.entity.Item;
import com.shop.arinlee.domain.item.service.ItemService;
import com.shop.arinlee.domain.member.Entity.Member;
import com.shop.arinlee.domain.member.service.MemberService;
import com.shop.arinlee.domain.order.entity.OrderItem;
import com.shop.arinlee.domain.order.service.OrderService;
import com.shop.arinlee.global.error.exception.BusinessException;
import com.shop.arinlee.global.error.exception.ErrorCode;
import com.shop.arinlee.web.itemdetail.dto.ItemDtlDto;
import com.shop.arinlee.web.itemdetail.dto.RegisterOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemDtlService {

    private final ItemService itemService;

    private final OrderService orderService;

    private final MemberService memberService;

    public ItemDtlDto getItemDtl(Long itemId) {

        Item item = itemService.findItemById(itemId);

        return ItemDtlDto.of(item);
    }

    @Transactional
    public void registerOrderItem(RegisterOrderDto registerOrderDto, Principal principal) {
        Member member = memberService.findByEmail(principal.getName());
        if (member==null){
            throw new BusinessException(ErrorCode.NO_MATCHING_MEMBER);
        }

        Item item = itemService.findItemById(registerOrderDto.getItemId());

        List<OrderItem> orderItemList = new ArrayList<>();
        addOrderItem(orderItemList, item, registerOrderDto.getCount());

        orderService.registerOrder(member, orderItemList);
    }

    private void addOrderItem(List<OrderItem> orderItemList, Item item, int count) {
        OrderItem orderItem = OrderItem.builder()
                .item(item)
                .count(count)
                .orderPrice(item.getPrice() * count)
                .build();
        itemService.reduceStock(item, count);
        orderItemList.add(orderItem);
    }
}