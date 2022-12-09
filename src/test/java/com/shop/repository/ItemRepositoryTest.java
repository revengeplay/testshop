package com.shop.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import com.shop.entity.QItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @PersistenceContext
    EntityManager em;

    @DisplayName("CreateItemTest")
    @Test
    public void CreateItemTest() {
        // given
        Item item = new Item();
        item.setItemName("테스트 상품");
        item.setPrice(10000);
        item.setRegTime(LocalDateTime.now());
        item.setStockNumber(100);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setUpdateTime(LocalDateTime.now());

        // when_then
        Item savedItem = itemRepository.save(item);
        System.out.println(savedItem.toString());


    }

    public void createItemList() {
        for (int i = 1; i < 10; i++) {
            Item item = new Item();
            item.setItemName("테스트 상품" + i);
            item.setPrice(10000 + i);
            item.setRegTime(LocalDateTime.now());
            item.setStockNumber(100);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
        }
    }

    @DisplayName("findByItemNameTest")
    @Test
    public void findByItemNameTest() {
        // given
        this.createItemList();

        // when_then
        List<Item> itemList = itemRepository.findByItemName("테스트 상품2");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }

    }

    @DisplayName("findByItemNameOrItemDetailTest")
    @Test
    public void findByItemNameOrItemDetailTest() {
        // given
        this.createItemList();

        // when_then
        List<Item> itemList = itemRepository.findByItemNameOrItemDetail("테스트 상품3", "테스트 상품 상세 설명4");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }


    }

    @DisplayName("findByPriceLessThanTest")
    @Test
    public void findByPriceLessThanTest() {
        // given
        this.createItemList();


        // when_then
        List<Item> itemList = itemRepository.findByPriceLessThan(10005);
        for (Item item : itemList) {
            System.out.println(item.toString());
        }

    }

//    @DisplayName("findByPriceLessThanOrderByPriceDesc")
//    @Test
//    public void findByPriceLessThanOrderByPriceDesc() {
//        // given
//        this.createItemList();
//        // when_then
//        List<Item> itemList = itemRepository.finByPriceLessThanOrderByPriceDesc(10005);
//        for (Item item : itemList) {
//            System.out.println(item.toString());
//        }
//    }

    @DisplayName("findByItemDetailTest")
    @Test
    public void findByItemDetailTest(){
        // given
        this.createItemList();

        // when_then
        List<Item> itemList = itemRepository.findByItemDetail("테스트 상품 상세 설명");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }

    }

    @DisplayName("findByItemDetailByNative")
    @Test
    public void findByItemDetailByNative(){
        // given
        this.createItemList();

        // when_then
        List<Item> itemList = itemRepository.findByItemDetailByNative("테스트 상품 상세 설명");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @DisplayName("queryDslTest")
    @Test
    public void queryDslTest(){
        // given
        this.createItemList();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QItem qItem = QItem.item;
        JPAQuery<Item> query = queryFactory.selectFrom(qItem)
                .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qItem.itemDetail.like("%" + "테스트 상품 상세 설명" + "%"))
                .orderBy(qItem.price.desc());

        // when_then
        List<Item> itemList = query.fetch();
        for (Item item : itemList) {
            System.out.println(item.toString());
        }

    }



}
