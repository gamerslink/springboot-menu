package com.liaojacky.springbootmenu.rowmapper;

import com.liaojacky.springbootmenu.model.OrderItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemRowMapper implements RowMapper<OrderItem> {

    @Override
    public OrderItem mapRow(ResultSet resultSet, int i) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemId(resultSet.getInt("orderItemId"));
        orderItem.setOrderId(resultSet.getInt("orderId"));
        orderItem.setProductId(resultSet.getInt("productId"));
        orderItem.setQuantity(resultSet.getInt("quantity"));
        orderItem.setAmount(resultSet.getInt("amount"));

        orderItem.setProductName(resultSet.getString("productName"));

        return orderItem;
    }


}
