package com.example.restaurant.service;

import com.example.restaurant.constants.OrderStatus;
import com.example.restaurant.entity.Order;
import com.example.restaurant.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order saveOrder(int itemId, int itemCount) {
        Order order = new Order();
        order.setItemId(itemId);
        order.setItemCount(itemCount);
        return orderRepository.save(order);
    }

    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public OrderStatus changeOrderStatus(Long orderId, OrderStatus orderStatus) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if(!orderOptional.isPresent()) {
            return OrderStatus.NOINFO;
        }
        Order order = orderOptional.get();
        order.setOrderStatus(orderStatus);
        order = orderRepository.save(order);
        return order.getOrderStatus();
    }
}
