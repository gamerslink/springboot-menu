package com.liaojacky.springbootmenu.service.Impl;

import com.liaojacky.springbootmenu.dao.OrderDao;
import com.liaojacky.springbootmenu.dto.OrderRequest;
import com.liaojacky.springbootmenu.model.Order;
import com.liaojacky.springbootmenu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public Order getOrderById(Integer orderId) {
        return orderDao.getOrderById(orderId);
    }

    @Override
    public Integer createOrder(OrderRequest orderRequest) {
        return orderDao.createOrder(orderRequest);
    }

    @Override
    public void updateOrder(Integer orderId, OrderRequest orderRequest) {
       orderDao.updateOrder(orderId, orderRequest);
    }

    @Override
    public void deleteOrderById(Integer orderId) {
        orderDao.deleteOrderById(orderId);
    }
}
