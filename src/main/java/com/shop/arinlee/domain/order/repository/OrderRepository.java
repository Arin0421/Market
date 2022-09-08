package com.shop.arinlee.domain.order.repository;

import com.shop.arinlee.domain.member.Entity.Member;
import com.shop.arinlee.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long>, OrderRepositoryCustom {
    List<Order> findByMember(Member member);
}
