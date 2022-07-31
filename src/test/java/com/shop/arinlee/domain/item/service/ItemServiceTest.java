package com.shop.arinlee.domain.item.service;

import com.shop.arinlee.domain.item.entity.Item;
import com.shop.arinlee.domain.item.entity.ItemSellStatus;
import com.shop.arinlee.domain.item.repository.ItemRepository;
import com.shop.arinlee.domain.member.Entity.Member;
import com.shop.arinlee.domain.member.Entity.Role;
import com.shop.arinlee.domain.member.Entity.Type;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @InjectMocks
    private ItemService target;

    @Mock
    private ItemRepository itemRepository;

    final Member member = Member.builder()
            .email("test@email.com")
            .address("서울특별시")
            .memberName("tester")
            .password("password")
            .memberType(Type.BASE)
            .role(Role.ADMIN)
            .build();

    @Test
    public void 아이템등록_실패(){
        //given
        final Item item = Item.builder()
                .itemName("상품")
                .itemDetail("설명")
                .stockNumber(3)
                .itemSellStatus(ItemSellStatus.SELL)
                .build();

        //when
        Item result = target.saveItem(item);

        //then
        assertThat(result).isNull();

    }

    @Test
    public void 아이템등록_성공(){
        //given
        final Item item = Item.builder()
                .itemName("상품")
                .itemDetail("설명")
                .stockNumber(3)
                .itemSellStatus(ItemSellStatus.SELL)
                .price(30000)
                .member(member)
                .build();
        doReturn(item).when(itemRepository).save(any(Item.class));

        //when
        final Item result = target.saveItem(item);

        //then
        assertThat(result).isEqualTo(item);
    }
}