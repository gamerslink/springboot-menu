package com.liaojacky.springbootmenu.service.Impl;

import com.liaojacky.springbootmenu.dao.OrderDao;
import com.liaojacky.springbootmenu.dao.ProductDao;
import com.liaojacky.springbootmenu.dao.UserDao;
import com.liaojacky.springbootmenu.dto.BuyItem;
import com.liaojacky.springbootmenu.dto.CreateOrderRequest;
import com.liaojacky.springbootmenu.dto.OrderRequest;
import com.liaojacky.springbootmenu.model.Order;
import com.liaojacky.springbootmenu.model.OrderItem;
import com.liaojacky.springbootmenu.model.Product;
import com.liaojacky.springbootmenu.model.User;
import com.liaojacky.springbootmenu.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;


    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public Order getOrderById(Integer orderId) {
        return orderDao.getOrderById(orderId);
    }

    // 萬一中間噴出Exception，會復原資料庫操作
    // 確保OrderDetail、order同時成功或是失敗,避免數據不一致的問題
    // 有多個修改table 一定要加上 Transactional
    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        // 檢查 user 是否存在
        User user = userDao.getUserById(userId);

        if (user == null) {
            log.warn("該 userId {} 不存在", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());

            // 檢查 product 是否存在、庫存是否足夠
            if (product == null) {
                log.warn("商品 {} 不存在", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else if (product.getStock() < buyItem.getQuantity()) {
                log.warn("商品 {} 庫存數量不足，無法購買。剩餘庫存 {} ，欲購買數量 {}",
                        buyItem.getProductId(), product.getStock(), buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }


            // 扣除商品庫存
            productDao.updateStock(product.getProductId(), product.getStock() - buyItem.getQuantity());



            // 計算總價錢
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount += amount;

            // 轉換 BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(buyItem.getProductId());
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }

        // 創建訂單
        Integer orderId = orderDao.createOrder(userId, totalAmount);

        orderDao.createOrderListItems(orderId, orderItemList);

        return orderId;

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
