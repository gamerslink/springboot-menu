package com.liaojacky.springbootmenu.dto;


import javax.validation.constraints.NotNull;

public class OrderRequest {

    @NotNull
    private Integer userId;

    @NotNull
    private Integer totalAmount;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }
}
