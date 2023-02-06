package com.liaojacky.springbootmenu.controller;

import com.liaojacky.springbootmenu.dto.OrderRequest;
import com.liaojacky.springbootmenu.model.Order;
import com.liaojacky.springbootmenu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer orderId){
        Order order = orderService.getOrderById(orderId);

        if (order != null) {
            return ResponseEntity.status(HttpStatus.OK).body(order);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 如果找不到 build 回傳給前端
        }
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody @Valid OrderRequest orderRequest) {
        Integer orderId = orderService.createOrder(orderRequest);
        Order order = orderService.getOrderById(orderId); // 查詢商品數據到order

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PutMapping("/orders/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable Integer orderId,
                                             @RequestBody @Valid OrderRequest orderRequest
                                             ) {

        // 檢查商品是否存在
        Order order = orderService.getOrderById(orderId);
        if ( order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


        // 修改商品
        orderService.updateOrder(orderId, orderRequest); // 不會返回值

        Order updateOrder = orderService.getOrderById(orderId); // 查詢更新後的數據

        return ResponseEntity.status(HttpStatus.OK).body(updateOrder);
    }

}
