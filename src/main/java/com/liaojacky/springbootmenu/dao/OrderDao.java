package com.liaojacky.springbootmenu.dao;

import com.liaojacky.springbootmenu.dto.OrderRequest;
import com.liaojacky.springbootmenu.model.Order;

public interface OrderDao {

    Order getOrderById(Integer orderId);
    Integer createOrder(OrderRequest orderRequest);
    void updateOrder(Integer orderId, OrderRequest orderRequest);
}
