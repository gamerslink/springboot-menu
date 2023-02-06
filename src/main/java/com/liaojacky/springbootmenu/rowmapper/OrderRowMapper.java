package com.liaojacky.springbootmenu.rowmapper;

import com.liaojacky.springbootmenu.model.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        Order order = new Order();
        order.setOrderId(resultSet.getInt("orderId"));
        order.setUserId(resultSet.getInt("userId"));
        order.setTotalAmount(resultSet.getInt("totalAmount"));
        return order;
    }
}
