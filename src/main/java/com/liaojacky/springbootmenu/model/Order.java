package com.liaojacky.springbootmenu.model;


public class Order {
    private Integer orderId;
    private Integer buyerName;
    private Integer orderAmount;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(Integer buyerName) {
        this.buyerName = buyerName;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }
}
