package com.example.restaurant.data;

import com.example.restaurant.constants.DeliveryBoyStatus;
import com.example.restaurant.entity.DeliveryBoy;
import com.example.restaurant.entity.Restaurant;
import com.example.restaurant.repository.DeliveryBoyRepository;
import com.example.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class DummyDataCreationService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private DeliveryBoyRepository deliveryBoyRepository;

    public void createData() {
        // 1 restaurant and 2 delivery boys
        HashMap<Long, Long> map = new HashMap<>();
        map.put(1L,8L);
        map.put(2L,16L);
        map.put(3L,12L);
        Restaurant restaurant = new Restaurant();
        restaurant.setItemMap(map);
        restaurant = restaurantRepository.save(restaurant);
        List<DeliveryBoy> deliveryBoys = new ArrayList<>();
        DeliveryBoy deliveryBoy1 = new DeliveryBoy();
        deliveryBoy1.setDeliveryBoyStatus(DeliveryBoyStatus.ACTIVE);
        deliveryBoy1.setRestaurant(restaurant);
        deliveryBoys.add(deliveryBoy1);
        DeliveryBoy deliveryBoy2 = new DeliveryBoy();
        deliveryBoy2.setDeliveryBoyStatus(DeliveryBoyStatus.ACTIVE);
        deliveryBoy2.setRestaurant(restaurant);
        deliveryBoys.add(deliveryBoy2);
        deliveryBoyRepository.saveAll(deliveryBoys);

        // 2 restaurant and 4 delivery boys
        map.clear();
        map.put(4L,8L);
        map.put(5L,16L);
        map.put(6L,12L);
        restaurant = new Restaurant();
        restaurant.setItemMap(map);
        restaurant = restaurantRepository.save(restaurant);
        deliveryBoys.clear();
        deliveryBoy1 = new DeliveryBoy();
        deliveryBoy1.setDeliveryBoyStatus(DeliveryBoyStatus.ACTIVE);
        deliveryBoy1.setRestaurant(restaurant);
        deliveryBoys.add(deliveryBoy1);
        deliveryBoy2 = new DeliveryBoy();
        deliveryBoy2.setDeliveryBoyStatus(DeliveryBoyStatus.ACTIVE);
        deliveryBoy2.setRestaurant(restaurant);
        deliveryBoys.add(deliveryBoy2);
        DeliveryBoy deliveryBoy3 = new DeliveryBoy();
        deliveryBoy3.setDeliveryBoyStatus(DeliveryBoyStatus.BUSY);
        deliveryBoy3.setRestaurant(restaurant);
        deliveryBoys.add(deliveryBoy3);
        DeliveryBoy deliveryBoy4 = new DeliveryBoy();
        deliveryBoy4.setDeliveryBoyStatus(DeliveryBoyStatus.INACTIVE);
        deliveryBoy4.setRestaurant(restaurant);
        deliveryBoys.add(deliveryBoy4);
        deliveryBoyRepository.saveAll(deliveryBoys);
    }
}
