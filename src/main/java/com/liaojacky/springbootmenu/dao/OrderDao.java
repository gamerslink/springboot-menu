package com.liaojacky.springbootmenu.dao;

import com.liaojacky.springbootmenu.model.Order;

public interface OrderDao {

    Order getOrderById(Integer orderId);
}
