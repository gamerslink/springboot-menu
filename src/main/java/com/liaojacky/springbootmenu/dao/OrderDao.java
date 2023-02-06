package com.liaojacky.springbootmenu.dao;

import com.liaojacky.springbootmenu.dto.OrderRequest;
import com.liaojacky.springbootmenu.model.Order;
import com.liaojacky.springbootmenu.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemByOrderById(Integer orderId);

    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderListItems(Integer orderId, List<OrderItem> orderItemList);

    void updateOrder(Integer orderId, OrderRequest orderRequest);

    void deleteOrderById(Integer orderId);
}
