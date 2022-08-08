package com.shop.arinlee.web.adminItem.service;

import com.shop.arinlee.domain.ItemImage.service.ItemImageService;
import com.shop.arinlee.domain.item.entity.Item;
import com.shop.arinlee.domain.item.service.ItemService;
import com.shop.arinlee.domain.member.Entity.Member;
import com.shop.arinlee.domain.member.service.MemberService;
import com.shop.arinlee.web.adminItem.dto.AdminItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.Principal;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminItemService {

    private final ItemService itemService;
    private final ItemImageService itemImageService;
    private final MemberService memberService;

    @Transactional
    public Long addNewAdminItem(AdminItemDto adminItemDto, Principal principal) throws IOException {
        Member member = findMemberByPrincipal(principal);
        Item item = adminItemDto.toItemEntity(member);
        itemService.saveItem(item);

        itemImageService.saveItemImages(adminItemDto.getItemImageFiles(), item);
        return item.getId();
    }

    private Member findMemberByPrincipal(Principal principal) {
        String email = principal.getName();
        Member member = memberService.findByEmail(email);
                //.orElseThrow(() -> new BusinessException(ErrorCode.NO_MATCHING_MEMBER));
        return member;
    }
}