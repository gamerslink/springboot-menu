package com.liaojacky.springbootmenu.dto;


import javax.validation.constraints.NotNull;

public class ProductRequest {

    @NotNull
    private String productName;

    @NotNull
    private Integer price;

    @NotNull
    private Integer stock;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
