package com.liaojacky.springbootmenu.service;

import com.liaojacky.springbootmenu.dto.OrderRequest;
import com.liaojacky.springbootmenu.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);
    Integer createOrder(OrderRequest orderRequest);
}
