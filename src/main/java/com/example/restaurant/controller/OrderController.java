package com.example.restaurant.controller;

import com.example.restaurant.constants.OrderStatus;
import com.example.restaurant.entity.Order;
import com.example.restaurant.handler.NoDataFoundException;
import com.example.restaurant.model.OrderResponse;
import com.example.restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public OrderResponse placeOrder(@RequestParam("itemId") int itemId, @RequestParam("noOfItems")int itemCount) {
        Order order = orderService.saveOrder(itemId, itemCount);
        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getId());
        response.setOrderStatus(order.getOrderStatus());
        return response;
    }

    @GetMapping("/orderStatus")
    public OrderStatus getOrderStatusById(@RequestParam("orderId") long orderId) {
        Optional<Order> order = orderService.getOrderById(orderId);
        if(order.isPresent()) {
            return order.get().getOrderStatus();
        } else {
            throw new NoDataFoundException("No data present for order with id - "+orderId);
        }
    }

    @PostMapping("/order/deliveryStatus")
    public OrderStatus orderDeliveryStatus(@RequestParam("orderId") Long orderId, @RequestParam("status")OrderStatus orderStatus) {
        return orderService.changeOrderStatus(orderId, orderStatus);
    }
}
