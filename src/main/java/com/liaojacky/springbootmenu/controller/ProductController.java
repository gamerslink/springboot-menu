package com.liaojacky.springbootmenu.controller;

import com.liaojacky.springbootmenu.dto.ProductRequest;
import com.liaojacky.springbootmenu.model.Order;
import com.liaojacky.springbootmenu.model.Product;
import com.liaojacky.springbootmenu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);

        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        Integer productId = productService.createProduct(productRequest); // 返回資料庫生成的ID

        Product product = productService.getProductById(productId); // 查詢商品的數據回來

        return ResponseEntity.status(HttpStatus.CREATED).body(product); // 201

    }


}
