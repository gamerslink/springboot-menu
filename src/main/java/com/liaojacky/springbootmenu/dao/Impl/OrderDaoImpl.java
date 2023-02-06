package com.liaojacky.springbootmenu.dao.Impl;

import com.liaojacky.springbootmenu.dao.OrderDao;
import com.liaojacky.springbootmenu.dto.OrderQueryParams;
import com.liaojacky.springbootmenu.dto.OrderRequest;
import com.liaojacky.springbootmenu.model.Order;
import com.liaojacky.springbootmenu.model.OrderItem;
import com.liaojacky.springbootmenu.rowmapper.OrderItemRowMapper;
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
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        String sql = "SELECT count(*) FROM `order` WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        // 查詢條件
        sql = addFilteringSql(sql, map, orderQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
        String sql = "SELECT orderId, userId, totalAmount FROM `order` WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        // 查詢條件
        sql = addFilteringSql(sql, map, orderQueryParams);

        // 排序
        sql = sql + " ORDER BY orderId DESC";

        // 分頁
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit", orderQueryParams.getLimit());
        map.put("offset", orderQueryParams.getOffset());

        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());

        return orderList;
    }



    @Override
    public List<OrderItem> getOrderItemByOrderById(Integer orderId) {
        String sql = "SELECT oi.orderItemId, oi.orderId, oi.productId, oi.quantity, oi.amount, p.productName " +
                "FROM orderItem as oi LEFT JOIN product as p ON oi.productId = p.productId WHERE oi.orderId = :orderId";

        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);

        List<OrderItem> orderItemList = namedParameterJdbcTemplate.query(sql, map, new OrderItemRowMapper());

        return orderItemList;
    }

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


    private String addFilteringSql(String sql, Map<String, Object> map, OrderQueryParams orderQueryParams) {
        if (orderQueryParams.getUserId() != null) {
            sql = sql + " AND userId = :userId";
            map.put("userId", orderQueryParams.getUserId());
        }

        return sql;
    }

}
