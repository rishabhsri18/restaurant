package com.example.restaurant.entity;

import com.example.restaurant.constants.OrderStatus;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "restaurant_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int itemId;

    @Column
    private int itemCount;

    @Column
    private int timeDurationToDeliver=20;

    @Column
    private OrderStatus orderStatus = OrderStatus.INITIATED;

    @Column
    private long restaurantId;

    @Column
    private long deliveryBoyId;

}
