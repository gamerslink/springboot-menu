package com.liaojacky.springbootmenu.dao;

import com.liaojacky.springbootmenu.dto.ProductRequest;
import com.liaojacky.springbootmenu.model.Product;


public interface ProductDao {

    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
}
