package com.example.restaurant.service;

import com.example.restaurant.constants.ApplicationConstants;
import com.example.restaurant.constants.DeliveryBoyStatus;
import com.example.restaurant.constants.OrderStatus;
import com.example.restaurant.entity.DeliveryBoy;
import com.example.restaurant.entity.Order;
import com.example.restaurant.entity.Restaurant;
import com.example.restaurant.repository.DeliveryBoyRepository;
import com.example.restaurant.repository.OrderRepository;
import com.example.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryBoyService {

    @Autowired
    private DeliveryBoyRepository deliveryBoyRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private OrderRepository orderRepository;

    public DeliveryBoy saveDeliveryBoy(DeliveryBoy deliveryBoy) {
        return deliveryBoyRepository.save(deliveryBoy);
    }

    public Optional<DeliveryBoy> getDeliveryBoyById(Long id) {
        return deliveryBoyRepository.findById(id);
    }

    public Optional<DeliveryBoy> getDeliveryBoyByRestaurantId(Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        return deliveryBoyRepository.findByRestaurant(restaurant.get());
    }

    @Transactional
    public OrderStatus setOrderForDeliveryBoy(Long deliveryBoyId, Long orderId) {
        Optional<DeliveryBoy> deliveryBoy = deliveryBoyRepository.findById(deliveryBoyId);
        if(!deliveryBoy.isPresent() || deliveryBoy.get().getDeliveryBoyStatus() != DeliveryBoyStatus.ACTIVE) {
            return OrderStatus.PENDING;
        }
        Optional<Order> order = orderRepository.findById(orderId);
        if(!order.isPresent()) {
            return OrderStatus.PENDING;
        }

        return setOrderDataForDelivery(order.get(), deliveryBoy.get());
    }

    public Optional<List<DeliveryBoy>> getAllActiveDeliveryBoy() {
        return deliveryBoyRepository.findByDeliveryBoyStatus(DeliveryBoyStatus.ACTIVE);
    }

    private OrderStatus setOrderDataForDelivery(Order order, DeliveryBoy deliveryBoy) {
        deliveryBoy.setDeliveryBoyStatus(DeliveryBoyStatus.BUSY);
        deliveryBoy.setDeliveryTime(ApplicationConstants.DELIVERYTIME);
        deliveryBoyRepository.save(deliveryBoy);
        order.setDeliveryBoyId(deliveryBoy.getId());
        order.setRestaurantId(deliveryBoy.getRestaurant().getId());
        order.setOrderStatus(OrderStatus.ACCEPTED);
        orderRepository.save(order);
        return OrderStatus.ACCEPTED;
    }
}
