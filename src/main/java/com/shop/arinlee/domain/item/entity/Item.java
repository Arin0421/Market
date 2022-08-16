package com.shop.arinlee.domain.item.entity;

import com.shop.arinlee.domain.ItemImage.entity.ItemImage;
import com.shop.arinlee.domain.member.Entity.Member;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String itemName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stockNumber;

    @Lob
    @Column(nullable = false)
    private String itemDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id",nullable = false)
    private Member member;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItemImage> imageList;

    @Builder
    public Item(String itemName, ItemSellStatus itemSellStatus, int price, int stockNumber, String itemDetail, Member member) {
        this.itemName = itemName;
        this.itemSellStatus = itemSellStatus;
        this.price = price;
        this.stockNumber = stockNumber;
        this.itemDetail = itemDetail;
        this.member = member;
    }

    public void update(Item updateItem) {
        this.itemName = updateItem.itemName;
        this.itemSellStatus = updateItem.itemSellStatus;
        this.price = updateItem.price;
        this.stockNumber = updateItem.stockNumber;
        this.itemDetail = updateItem.itemDetail;
    }
}
