package com.shop.arinlee.domain.order.repository;

import com.shop.arinlee.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
