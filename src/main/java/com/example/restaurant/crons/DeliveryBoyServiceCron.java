package com.example.restaurant.crons;

import com.example.restaurant.constants.DeliveryBoyStatus;
import com.example.restaurant.constants.OrderStatus;
import com.example.restaurant.entity.DeliveryBoy;
import com.example.restaurant.entity.Order;
import com.example.restaurant.repository.DeliveryBoyRepository;
import com.example.restaurant.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryBoyServiceCron {
    private List<DeliveryBoy> deliveryBoyList;

    @Autowired
    private DeliveryBoyRepository deliveryBoyRepository;

    @Autowired
    private OrderRepository orderRepository;

    @PostConstruct
    @Scheduled(cron = "0 0 * * * *")
    private void init() {
        System.out.println("Scheduler running to fetch data delivery boy data table");
        deliveryBoyList = deliveryBoyRepository.findAll();

        // will update the status of delivery boy as well
        for(DeliveryBoy deliveryBoy:deliveryBoyList) {
            if(deliveryBoy.getDeliveryBoyStatus()== DeliveryBoyStatus.BUSY) {
                Optional<Order> optionalOrder = orderRepository.findByDeliveryBoyId(deliveryBoy.getId());
                if(deliveryBoy.getDeliveryTime() <= 1) {
                    deliveryBoy.setDeliveryTime(0);
                    deliveryBoy.setDeliveryBoyStatus(DeliveryBoyStatus.ACTIVE);
                    if(optionalOrder.isPresent()) {
                        Order order = optionalOrder.get();
                        order.setOrderStatus(OrderStatus.DELIVERED);
                        orderRepository.save(order);
                    }
                } else {
                    deliveryBoy.setDeliveryTime(deliveryBoy.getDeliveryTime()-1);
                }
            }
        }

        deliveryBoyRepository.saveAll(deliveryBoyList);
    }
}
