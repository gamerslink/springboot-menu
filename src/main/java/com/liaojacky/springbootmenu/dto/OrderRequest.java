package com.liaojacky.springbootmenu.dto;


import javax.validation.constraints.NotNull;

public class OrderRequest {

    @NotNull
    private String buyerName;

    @NotNull
    private Integer orderAmount;

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }
}
