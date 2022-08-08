package com.shop.arinlee.domain.item.repository;

import com.shop.arinlee.domain.item.entity.Item;
import com.shop.arinlee.domain.item.entity.ItemSellStatus;
import com.shop.arinlee.domain.member.Entity.Member;
import com.shop.arinlee.domain.member.Entity.Role;
import com.shop.arinlee.domain.member.Entity.Type;
import com.shop.arinlee.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MemberRepository memberRepository;

    final Member member = Member.builder()
            .email("test@email.com")
            .address("서울특별시")
            .memberName("tester")
            .password("password")
            .memberType(Type.BASE)
            .role(Role.USER.ADMIN)
            .build();

    @BeforeEach
    public void init() {
        memberRepository.save(member);
    }

    @Test
    public void ItemRepository가Null이아님() {
        assertThat(itemRepository).isNotNull();
    }

    @Test
    public void 아이템등록() {
        //given
        final Item item = Item.builder()
                .itemName("상품명")
                .itemDetail("상품설명")
                .itemSellStatus(ItemSellStatus.SELL)
                .price(30000)
                .stockNumber(2)
                .member(member)
                .build();

        //when
        final Item result = itemRepository.save(item);

        //then
        assertThat(result).isEqualTo(item);

    }
}