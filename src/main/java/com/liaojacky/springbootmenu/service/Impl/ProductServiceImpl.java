package com.liaojacky.springbootmenu.service.Impl;

import com.liaojacky.springbootmenu.dao.ProductDao;
import com.liaojacky.springbootmenu.dto.ProductRequest;
import com.liaojacky.springbootmenu.model.Product;
import com.liaojacky.springbootmenu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getProducts() {
       return productDao.getProducts();
    }

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

}
