package com.shop.repository;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @DisplayName("Test")
    @Test
    public void CreateItemTest() {
        // given
        Item item = new Item();
        item.setItemName("테스트 상품");
        item.setPrice(10000);
        item.setRegTime(LocalDateTime.now());
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setUpdateTime(LocalDateTime.now());

        // when_then
        Item savedItem = itemRepository.save(item);
        System.out.println(savedItem.toString());


    }


}
