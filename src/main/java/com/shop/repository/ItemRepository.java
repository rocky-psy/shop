package com.shop.repository;


import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {

    /* 쿼리 메소드
    *
    * 데이터 조회 기능 - JPA에서 제공하는 핵심 기능 중 하나로 간단한 네이밍 룰을 이용하여 원하는 쿼리를 실행할 수 있음.
    *  find + (엔티티 이름) + By + 변수이름  -> 엔티티 이름은 생략 가능 ..
    *
    * */

    List<Item> findByItemNm(String itemNm);

    /**
     * JPQL
     * */
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetailContaining( @Param("itemDetail") String itemDetail);

}
