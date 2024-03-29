package com.shop.arinlee.domain.order.service;

import com.shop.arinlee.domain.member.Entity.Member;
import com.shop.arinlee.domain.order.entity.Order;
import com.shop.arinlee.domain.order.entity.OrderItem;
import com.shop.arinlee.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository ordersRepository;

    @Transactional
    public void registerOrder(Member member, List<OrderItem> orderItemList) {
        Order order = Order.createOrder(member, orderItemList, LocalDateTime.now());
        ordersRepository.save(order);
    }

}