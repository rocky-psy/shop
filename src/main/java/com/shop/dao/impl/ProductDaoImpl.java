package com.shop.dao.impl;

import com.shop.dao.ProductDao;
import com.shop.entity.product.Product;
import com.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {

    private final ProductRepository productRepository;

    @Override
    public Product insertProduct(Product product) {

        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public Product selectProduct(Long number) {

        Optional<Product> optionalProduct = productRepository.findById(number);

        /**
         *  getById 메서드와 findById 메서드 차이
         *
         * getById(id)
         *
         * JPA 2.x 기준: 프록시 객체를 반환
         * 즉시 DB를 조회하지 않고, 실제 값이 필요할 때 DB 조회 (Lazy Loading)
         * 만약 해당 ID의 데이터가 없으면, 접근 시점에 예외 발생 (EntityNotFoundException)
         *
         * findById(id)
         *
         * 즉시 DB를 조회하여 결과를 Optional<T>로 반환
         * 존재 여부를 확인하기에 더 안전하고 명확한 방법
         * 내부적으로 EntityManager 의 find() 메서드를 호출합니다.
         * 영속성 컨텍스트의 캐시에서 값을 조회한 후 영속성 컨텍스트에 값이 존재하지 않는다면 실제 데이터베이스에서 데이터를 조회합니다.
         * 리턴값으로 Optional 객체를 전달한다.
         *
         *
         * Optional<T>는 Java 8에서 도입된 **null 안전(null-safety)**을 위한 컨테이너 클래스입니다.
         * 간단히 말해:
         *
         * 🔹 Optional<T>는 “T라는 값이 있을 수도 있고, 없을 수도 있다”는 걸 표현하는 **객체 포장(wrapper)**입니다.
         * 🛠️ 주요 메서드 요약
         * 메서드	설명
         * Optional.of(value)	값이 절대 null이 아님을 보장할 때 사용
         * Optional.ofNullable(value)	값이 null일 수도 있을 때 사용
         * isPresent()	값이 존재하면 true
         * ifPresent(Consumer)	값이 존재하면 실행
         * orElse(default)	값이 없으면 기본값(null) 리턴
         * orElseGet(Supplier)	값이 없으면 함수형 인터페이스로 기본값 생성
         * orElseThrow()	값이 없으면 예외 발생
         */
        return optionalProduct.orElse(null);
    }

    @Override
    public Product updateProductName(Long number, String name) throws Exception {
        Optional<Product> selectProduct = productRepository.findById(number);

        Product updatedProduct;

        if(selectProduct.isPresent()){
            //Optional 객체 메서드 isPresent() : 값이 존재하면 true

            Product product = selectProduct.get();

            product.setName(name);
            product.setUpdatedAt(LocalDateTime.now());

            updatedProduct = productRepository.save(product);

        }else{
            throw new Exception();
        }

        return updatedProduct;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {

        Optional<Product> selectedProduct = productRepository.findById(number);

        if(selectedProduct.isPresent()){
            productRepository.deleteById(number);

            //selectedProduct.get().delete();

        }else{
            throw new Exception();
        }

    }
}
