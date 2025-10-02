package com.shop.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.entity.Item;
import com.shop.entity.ItemSellStatus;
import com.shop.entity.QItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import org.hibernate.validator.internal.engine.resolver.JPATraversableResolver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.annotation.Rollback;
import org.thymeleaf.standard.expression.Each;

import java.time.LocalDateTime;
import java.util.List;



import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("상품 저장 테스트")
    public void 상품저장테스트() throws Exception {
        //given
        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        Item savedItem = itemRepository.save(item);

    System.out.println(savedItem.toString());

    }


    @Test
    @Rollback(false)
    public void 상품리스트저장테스트(){
        //given
        for (int i = 1; i <= 10; i++) {

            Item item = new Item();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);

        }
        //when

        //then
    }


    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNmTest(){
        this.상품리스트저장테스트();
        List<Item> itemList = itemRepository.findByItemNm("테스트 상품1");

        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("QueryDsl 조회 테스트1")
    public void  queryDslTest() throws Exception {
        //given
        this.상품리스트저장테스트();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QItem qItem = QItem.item;
        JPAQuery<Item> query = queryFactory.selectFrom(qItem)
                .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qItem.itemDetail.like("%" + "테스트 상품 상세 설명" + "%"))
                .orderBy(qItem.price.desc());


        List<Item> itemList = query.fetch();

        for(Item item : itemList){
            System.out.println(item.toString());

        }
        //when

        //then


    }


}