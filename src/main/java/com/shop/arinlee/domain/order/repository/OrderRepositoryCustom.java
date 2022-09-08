package com.shop.arinlee.domain.order.repository;

import com.shop.arinlee.domain.member.Entity.Member;
import com.shop.arinlee.web.orderhist.dto.OrderHistDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepositoryCustom {
    Page<OrderHistDto> getOrderHistByMember(Member member, Pageable pageable);
}
