package com.shop.arinlee.web.adminItem.controller;

import com.shop.arinlee.domain.item.entity.ItemSellStatus;
import com.shop.arinlee.web.adminItem.dto.RegisterItemDto;
import com.shop.arinlee.web.adminItem.dto.UpdateItemDto;
import com.shop.arinlee.web.adminItem.service.AdminItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class AdminItemControllerTest {
    @InjectMocks
    private AdminItemController target;
    @Mock
    private AdminItemService adminItemService;
    @Mock
    private Principal principal;
    private MockMvc mockMvc;
    final String itemName = "itemName";
    final Integer price = 3000;
    final String itemDetail = "details";
    final Integer stockNumber = 100;
    final ItemSellStatus itemSellStatus = ItemSellStatus.SOLD_OUT;
    final String fileName = "testImage";
    final String contentType = "png";
    final String filePath = "src/test/resources/image/testImage.png";
    RegisterItemDto adminItemDto = RegisterItemDto.builder()
            .itemName("itemName")
            .price(30)
            .itemDetail("details")
            .stockNumber(30)
            .itemSellStatus(ItemSellStatus.SOLD_OUT)
            .build();
    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(target)
                .build();
    }

    @Test
    public void 상품조회뷰반환테스트() throws Exception {
        //given
        doReturn(UpdateItemDto.builder().build()).when(adminItemService).getItemAndImages(any(long.class));
        final String url = "/admin/items/1";

        //when
        ResultActions resultActions = mockMvc.perform(get(url))
                .andDo(print());

        //then
        resultActions
                .andExpect(model().attributeExists("updateItemDto"))
                .andExpect(view().name("adminitem/updateitemform"));
    }
}