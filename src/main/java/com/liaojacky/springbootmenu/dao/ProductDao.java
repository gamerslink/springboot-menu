package com.liaojacky.springbootmenu.dao;

import com.liaojacky.springbootmenu.model.Product;


public interface ProductDao {

    Product getProductById(Integer productId);
}
