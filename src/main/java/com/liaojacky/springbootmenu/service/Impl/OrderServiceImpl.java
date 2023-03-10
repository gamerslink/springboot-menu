package com.liaojacky.springbootmenu.service.Impl;

import com.liaojacky.springbootmenu.dao.OrderDao;
import com.liaojacky.springbootmenu.dao.ProductDao;
import com.liaojacky.springbootmenu.dao.UserDao;
import com.liaojacky.springbootmenu.dto.BuyItem;
import com.liaojacky.springbootmenu.dto.CreateOrderRequest;
import com.liaojacky.springbootmenu.dto.OrderQueryParams;
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
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        return orderDao.countOrder(orderQueryParams);
    }

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
        List<Order> orderList = orderDao.getOrders(orderQueryParams);

        for (Order order : orderList) {
            List<OrderItem> orderItemList = orderDao.getOrderItemByOrderById(order.getOrderId());

            order.setOrderItemList(orderItemList);
        }

        return orderList;
    }




    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemByOrderById(orderId);

        order.setOrderItemList(orderItemList);

        return order;
    }


    // ??????????????????Exception???????????????????????????
    // ??????OrderDetail???order????????????????????????,??????????????????????????????
    // ???????????????table ??????????????? Transactional
    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        // ?????? user ????????????
        User user = userDao.getUserById(userId);

        if (user == null) {
            log.warn("??? userId {} ?????????", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());

            // ?????? product ?????????????????????????????????
            if (product == null) {
                log.warn("?????? {} ?????????", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else if (product.getStock() < buyItem.getQuantity()) {
                log.warn("?????? {} ???????????????????????????????????????????????? {} ?????????????????? {}",
                        buyItem.getProductId(), product.getStock(), buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }


            // ??????????????????
            productDao.updateStock(product.getProductId(), product.getStock() - buyItem.getQuantity());



            // ???????????????
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount += amount;

            // ?????? BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(buyItem.getProductId());
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }

        // ????????????
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
