package com.shop.dao;

import com.shop.entity.product.Product;

public interface ProductDao {

    Product insertProduct(Product product);

    Product selectProduct(Long number);

    Product updateProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;




}
