package com.shop.arinlee.domain.order.repository;


import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.arinlee.domain.member.Entity.Member;
import com.shop.arinlee.web.orderhist.dto.OrderHistDto;
import com.shop.arinlee.web.orderhist.dto.OrderHistDto.OrderItemHistDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.shop.arinlee.domain.ItemImage.entity.QItemImage.itemImage;
import static com.shop.arinlee.domain.item.entity.QItem.item;
import static com.shop.arinlee.domain.order.entity.QOrder.order;
import static com.shop.arinlee.domain.order.entity.QOrderItem.orderItem;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OrderRepositoryImpl implements  OrderRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<OrderHistDto> getOrderHistByMember(Member member, Pageable pageable) {
        List<OrderHistDto> result = queryFactory.select(
                        Projections.constructor(
                                OrderHistDto.class,
                                order.id,
                                order.orderTime,
                                order.orderStatus
                        )).from(order)
                .where(order.member.eq(member))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .distinct()
                .fetch();

        for (OrderHistDto orderHistDto : result) {
            orderHistDto.setTotalPrice(getTotalPrice(orderHistDto.getOrderId()));
            orderHistDto.setOrderItemHistDtos(getOrderItemHistDto(orderHistDto.getOrderId()));
        }

        Long size = queryFactory.select(order.count())
                .from(order)
                .where(order.member.eq(member))
                .fetchOne();

        return new PageImpl<>(result, pageable, size);
    }

    private Integer getTotalPrice(Long orderId){
        return queryFactory.select(orderItem.orderPrice.sum())
                .from(orderItem)
                .join(orderItem.order, order)
                .where(order.id.eq(orderId))
                .groupBy(orderItem.order)
                .fetchOne();
    }

    private List<OrderItemHistDto> getOrderItemHistDto(Long orderId) {
        return queryFactory.select(
                        Projections.constructor(
                                OrderItemHistDto.class,
                                item.itemName,
                                orderItem.count,
                                orderItem.orderPrice,
                                getRepImageUrl()
                        )).from(orderItem)
                .join(orderItem.item, item)
                .where(orderItem.order.id.eq(orderId))
                .fetch();
    }

    private JPQLQuery<String> getRepImageUrl() {
        return JPAExpressions.select(itemImage.imageUrl)
                .from(itemImage)
                .join(item.imageList, itemImage)
                .where(itemImage.isRepImage.isTrue())
                .distinct();
    }
}