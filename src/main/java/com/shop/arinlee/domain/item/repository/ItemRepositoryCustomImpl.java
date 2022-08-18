package com.shop.arinlee.domain.item.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.arinlee.domain.ItemImage.entity.QItemImage;
import com.shop.arinlee.domain.item.entity.QItem;
import com.shop.arinlee.web.main.dto.ItemSearchDto;
import com.shop.arinlee.web.main.dto.MainItemDto;
import com.shop.arinlee.web.main.dto.QMainItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

import java.util.List;

import static com.shop.arinlee.domain.ItemImage.entity.QItemImage.itemImage;
import static com.shop.arinlee.domain.item.entity.QItem.item;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryCustomImpl implements ItemRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    private BooleanExpression itemNameLike(String searchQuery){
        return StringUtils.isEmpty(searchQuery)?null: item.itemName.contains(searchQuery);
    }

    @Override
    public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable){

        QueryResults<MainItemDto> results = queryFactory
                .select(
                        new QMainItemDto(
                                item.id,
                                item.itemName,
                                item.itemDetail,
                                itemImage.imageUrl,
                                item.price)
                )
                .from(itemImage)
                .join(itemImage.item,item)
                .where(itemImage.isRepImage.eq(true))
                .where(itemNameLike(itemSearchDto.getSearchQuery()))
                .orderBy(item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MainItemDto> content = results.getResults();
        long size = results.getTotal();
        return new PageImpl<>(content,pageable,size);


        /*List<MainItemDto> results = queryFactory
                .select(
                        new QMainItemDto(
                                item.id,
                                item.itemName,
                                item.itemDetail,
                                itemImage.imageUrl,
                                item.price)
                )
                .from(itemImage)
                .join(itemImage.item,item)
                .where(itemImage.isRepImage.eq(true)
                .and(itemNameLike(itemSearchDto.getSearchQuery())))
                .orderBy(item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long size = queryFactory.select(itemImage.count())
                .from(itemImage)
                .join(itemImage.item,item)
                .where(itemImage.isRepImage.eq(true)
                .and(itemNameLike(itemSearchDto.getSearchQuery())))
                .fetchOne();
*/
        /*Long size = queryFactory.select(item.count())
                .from(item)
                .join(item.imageList, itemImage)
                .on(itemImage.isRepImage.eq(true))
                .where(item.itemSellStatus.eq(ItemSellStatus.SELL)
                        .and(eqItemSearch(searchQuery)))
                .fetchOne();*/
    }
}
