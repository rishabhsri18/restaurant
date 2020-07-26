package com.example.restaurant.controller;

import com.example.restaurant.entity.Restaurant;
import com.example.restaurant.handler.NoDataFoundException;
import com.example.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/restaurant")
    public String saveRestaurant(@RequestBody Restaurant restaurantRequest) {
        Restaurant restaurant = restaurantService.saveRestaurant(restaurantRequest);
        return "New Restaurant created with id : "+restaurant.getId();
    }

    @GetMapping("/restaurant/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        Optional<Restaurant> restaurant = restaurantService.getRestaurantById(id);
        if(restaurant.isPresent()) {
            return restaurant.get();
        } else {
            throw new NoDataFoundException("Unable to find restaurant for id - "+id);
        }
    }

    @GetMapping("/restaurant")
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }
}
