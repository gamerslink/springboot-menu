package com.liaojacky.springbootmenu.dao.Impl;

import com.liaojacky.springbootmenu.dao.OrderDao;
import com.liaojacky.springbootmenu.dto.OrderRequest;
import com.liaojacky.springbootmenu.model.Order;
import com.liaojacky.springbootmenu.model.OrderItem;
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
        String sql = "SELECT orderId, userId, totalAmount " +
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
    public Integer createOrder(Integer userId, Integer totalAmount) {

        String sql = "INSERT INTO `order` (userId ,totalAmount) VALUES (:userId, :totalAmount)";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("totalAmount", totalAmount);

        KeyHolder keyHolder = new GeneratedKeyHolder(); // 儲存資料庫自動生成的 orderId

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int orderId = keyHolder.getKey().intValue();

        return orderId;
    }



    @Override
    public void updateOrder(Integer orderId, OrderRequest orderRequest) {
        String sql = "UPDATE `order` SET userId = :userId, totalAmount = :totalAmount WHERE orderId = :orderId";

        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);

        map.put("userId",orderRequest.getUserId());
        map.put("totalAmount", orderRequest.getTotalAmount());

        namedParameterJdbcTemplate.update(sql,map);

    }

    @Override
    public void deleteOrderById(Integer orderId) {
        String sql = "DELETE FROM `order` WHERE orderId = :orderId";

        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void createOrderListItems(Integer orderId, List<OrderItem> orderItemList) {
        String sql = "INSERT INTO orderItem (orderId, productId, quantity, amount) " +
                "VALUES (:orderId, :productId, :quantity, :amount)";

        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[orderItemList.size()];

        for (int i = 0; i < orderItemList.size() ; i++) {
            OrderItem orderItem = orderItemList.get(i);


            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("orderId", orderId);
            parameterSources[i].addValue("productId", orderItem.getProductId());
            parameterSources[i].addValue("quantity", orderItem.getQuantity());
            parameterSources[i].addValue("amount", orderItem.getAmount());

        }

        namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);
    }


}
