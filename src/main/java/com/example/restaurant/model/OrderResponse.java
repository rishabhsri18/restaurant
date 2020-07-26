package com.example.restaurant.model;

import com.example.restaurant.constants.OrderStatus;
import lombok.Data;

@Data
public class OrderResponse {
    Long orderId;
    OrderStatus orderStatus;
}
