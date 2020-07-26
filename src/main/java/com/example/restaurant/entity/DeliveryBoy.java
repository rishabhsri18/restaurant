package com.example.restaurant.entity;

import com.example.restaurant.constants.DeliveryBoyStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "delivery_boy")
public class DeliveryBoy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private DeliveryBoyStatus deliveryBoyStatus;

    /*
        Time is considered to be in minutes
     */
    @Column
    private int deliveryTime = 0;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
}
