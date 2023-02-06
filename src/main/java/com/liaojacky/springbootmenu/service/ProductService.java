package com.liaojacky.springbootmenu.service;

import com.liaojacky.springbootmenu.dto.ProductRequest;
import com.liaojacky.springbootmenu.model.Product;



public interface ProductService {

    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest); // 前端傳回的參數


}
