package com.shop.service.impl;

import com.shop.dao.ProductDao;
import com.shop.data.ProductDto;
import com.shop.data.ProductResponseDto;
import com.shop.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    @Override
    public ProductResponseDto getProduct(Long number) {
      //  this.productDao = productDao;
        return null;
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto product) {
        return null;
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception {
        return null;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {

    }
}
