package com.liaojacky.springbootmenu.service;

import com.liaojacky.springbootmenu.dto.CreateOrderRequest;
import com.liaojacky.springbootmenu.dto.OrderRequest;
import com.liaojacky.springbootmenu.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    void updateOrder(Integer orderId, OrderRequest orderRequest);

    void deleteOrderById(Integer orderId);
}
