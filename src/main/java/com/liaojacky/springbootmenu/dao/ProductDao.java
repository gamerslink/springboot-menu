package com.liaojacky.springbootmenu.dao;

import com.liaojacky.springbootmenu.dto.ProductRequest;
import com.liaojacky.springbootmenu.model.Product;

import java.util.List;


public interface ProductDao {

    List<Product> getProducts();

    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);

    void updateStock(Integer productId, Integer stock);
}
