package com.liaojacky.springbootmenu.dao.Impl;

import com.liaojacky.springbootmenu.dao.OrderDao;
import com.liaojacky.springbootmenu.dto.OrderRequest;
import com.liaojacky.springbootmenu.model.Order;
import com.liaojacky.springbootmenu.rowmapper.OrderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Order getOrderById(Integer orderId) {
        String sql = "SELECT orderId, buyerName, orderAmount " +
                "FROM `order` WHERE orderId = :orderId";

        Map<String, Object> map = new HashMap<>();
        map.put("orderId",orderId);
        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());

        if(orderList.size() >= 0) {
            return orderList.get(0);
        }
        return null;
    }

    @Override
    public Integer createOrder(OrderRequest orderRequest) {
        String sql = "INSERT INTO `order` (buyerName ,orderAmount) VALUES (:buyerName, :orderAmount)";

        Map<String, Object> map = new HashMap<>();
        map.put("buyerName", orderRequest.getBuyerName());
        map.put("orderAmount", orderRequest.getOrderAmount());

        KeyHolder keyHolder = new GeneratedKeyHolder(); // 儲存資料庫自動生成的 orderId

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int orderId = keyHolder.getKey().intValue();

        return orderId;
    }

    @Override
    public void updateOrder(Integer orderId, OrderRequest orderRequest) {
        String sql = "UPDATE `order` SET buyerName = :buyerName, orderAmount = :orderAmount WHERE orderId = :orderId";

        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);

        map.put("buyerName",orderRequest.getBuyerName());
        map.put("orderAmount", orderRequest.getOrderAmount());

        namedParameterJdbcTemplate.update(sql,map);

    }

    @Override
    public void deleteOrderById(Integer orderId) {
        String sql = "DELETE FROM `order` WHERE orderId = :orderId";

        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);

        namedParameterJdbcTemplate.update(sql, map);
    }
}
