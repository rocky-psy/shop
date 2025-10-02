package com.shop.repository;

import com.shop.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    //ProductRepository rk JpaRepository를 상속받을 때는 대상 엔티티와 기본값 타입을 지정해야 합니다.
    //대상 엔티티를 Product로 설정하고 해당 엔티티의 @Id 필드 타입인 Long을 설정하면 된다.



}
