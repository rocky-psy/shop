package com.shop.entity.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    /**
     * Transient : 엔티티 클래스에는 선언돼 있는 필드지만 데이터베이스에서는 필요 없을 경우 이 애너테이션을 사용해
     * 데이터베이스에서 이용하지 않게 할 수 있다.
     */
    @Transient
    private String noSave;


}
