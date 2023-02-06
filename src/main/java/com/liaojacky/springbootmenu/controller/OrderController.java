package com.liaojacky.springbootmenu.controller;

import com.liaojacky.springbootmenu.dto.CreateOrderRequest;
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


    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(
            @PathVariable Integer userId,
            @RequestBody @Valid CreateOrderRequest createOrderRequest){

       Integer orderId = orderService.createOrder(userId, createOrderRequest); // 返回資料庫創建的ID

        Order order = orderService.getOrderById(orderId);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }



    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer orderId){
        Order order = orderService.getOrderById(orderId);

        if (order != null) {
            return ResponseEntity.status(HttpStatus.OK).body(order);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 如果找不到 build 回傳給前端
        }
    }


    @PutMapping("/users/{userId}/orders")
    public ResponseEntity<Order> updateOrder(@PathVariable Integer orderId,
                                             @RequestBody @Valid OrderRequest orderRequest
                                             ) {
        // 檢查訂單是否存在
        Order order = orderService.getOrderById(orderId);
        if ( order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // 修改訂單
        orderService.updateOrder(orderId, orderRequest); // 不會返回值

        Order updateOrder = orderService.getOrderById(orderId); // 查詢更新後的數據

        return ResponseEntity.status(HttpStatus.OK).body(updateOrder);
    }


        @DeleteMapping("/orders/{orderId}")
        public ResponseEntity<Order> deleteOrder(@PathVariable Integer orderId) {
            orderService.deleteOrderById(orderId);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

}
