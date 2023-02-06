package com.liaojacky.springbootmenu.service;

import com.liaojacky.springbootmenu.dto.CreateOrderRequest;
import com.liaojacky.springbootmenu.dto.OrderQueryParams;
import com.liaojacky.springbootmenu.dto.OrderRequest;
import com.liaojacky.springbootmenu.model.Order;

import java.util.List;

public interface OrderService {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);


    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    void updateOrder(Integer orderId, OrderRequest orderRequest);

    void deleteOrderById(Integer orderId);
}
