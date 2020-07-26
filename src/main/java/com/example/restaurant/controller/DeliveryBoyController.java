package com.example.restaurant.controller;

import com.example.restaurant.constants.DeliveryBoyStatus;
import com.example.restaurant.constants.OrderStatus;
import com.example.restaurant.entity.DeliveryBoy;
import com.example.restaurant.entity.Restaurant;
import com.example.restaurant.handler.NoDataFoundException;
import com.example.restaurant.repository.RestaurantRepository;
import com.example.restaurant.service.DeliveryBoyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DeliveryBoyController {

    @Autowired
    private DeliveryBoyService deliveryBoyService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @PostMapping("/restaurant/{restaurantId}/deliveryBoy")
    public String saveDeliveryBoy(@PathVariable(value = "restaurantId") Long restaurantId, @RequestBody DeliveryBoy deliveryBoy) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if(restaurant.isPresent()) {
            deliveryBoy.setRestaurant(restaurant.get());
            DeliveryBoy deliveryBoy1 = deliveryBoyService.saveDeliveryBoy(deliveryBoy);
            return "Saved Data of deliveryBoy with id :: "+deliveryBoy1.getId();
        }
        return "Invalid restaurant id: "+restaurantId;
    }

    @GetMapping("/deliveryBoy/{id}")
    public DeliveryBoy getDeliveryBoyById(@PathVariable Long id) {
        Optional<DeliveryBoy> deliveryBoy = deliveryBoyService.getDeliveryBoyById(id);
        if(deliveryBoy.isPresent()) {
            return deliveryBoy.get();
        } else {
            throw new NoDataFoundException("No data present for delivery boy with id - "+id);
        }
    }

    @GetMapping("/deliveryBoy")
    public DeliveryBoy getDeliveryBoyByRestaurantId(@RequestParam Long restaurantId) {
        Optional<DeliveryBoy> deliveryBoy = deliveryBoyService.getDeliveryBoyByRestaurantId(restaurantId);
        if(deliveryBoy.isPresent()) {
            return deliveryBoy.get();
        } else {
            throw new NoDataFoundException("No data present for delivery boy for restaurant id - "+restaurantId);
        }
    }

    @GetMapping("/deliveryBoy/status")
    public DeliveryBoyStatus getDeliveryBoyStatusById(@RequestParam("deliveryPersonId") Long id) {
        Optional<DeliveryBoy> deliveryBoy = deliveryBoyService.getDeliveryBoyById(id);
        if(deliveryBoy.isPresent()) {
            return deliveryBoy.get().getDeliveryBoyStatus();
        } else {
            throw new NoDataFoundException("No data present for delivery boy with id - "+id);
        }
    }

    @PostMapping("/deliveryBoy/order")
    public OrderStatus setOrderForDeliveryBoy(@RequestParam("deliveryPersonId") Long id, @RequestParam("orderId") Long orderId) {
        return deliveryBoyService.setOrderForDeliveryBoy(id, orderId);
    }

    @GetMapping("/activeDeliveryBoy")
    public List<DeliveryBoy> getAllActiveDeliveryBoy() {
        Optional<List<DeliveryBoy>> deliveryBoyOptionalDeliveryBoyList = deliveryBoyService.getAllActiveDeliveryBoy();
        if(deliveryBoyOptionalDeliveryBoyList.isPresent()) {
            return deliveryBoyOptionalDeliveryBoyList.get();
        } else {
            throw new NoDataFoundException("No Active delivery boy");
        }
    }
}
