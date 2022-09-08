package com.shop.arinlee.web.orderhist.service;

import com.shop.arinlee.domain.member.Entity.Member;
import com.shop.arinlee.domain.member.service.MemberService;
import com.shop.arinlee.domain.order.service.OrderService;
import com.shop.arinlee.global.error.exception.ErrorCode;
import com.shop.arinlee.web.orderhist.dto.OrderHistDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class OrderHistService {

    private final MemberService memberService;
    private final OrderService orderService;

    @Transactional(readOnly = true)
    public Page<OrderHistDto> getOrderHistory(Principal principal, Pageable pageable) {
        Member member = getMember(principal);
        return orderService.getOrderHistPage(member, pageable);
    }

    private Member getMember(Principal principal) {

        return memberService.findByEmail(principal.getName());
    }
}