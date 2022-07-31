package com.shop.arinlee.domain.item.repository;


import com.shop.arinlee.domain.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
