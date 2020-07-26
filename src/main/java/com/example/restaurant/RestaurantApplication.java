package com.example.restaurant;

import com.example.restaurant.constants.DeliveryBoyStatus;
import com.example.restaurant.data.DummyDataCreationService;
import com.example.restaurant.entity.DeliveryBoy;
import com.example.restaurant.entity.Restaurant;
import com.example.restaurant.repository.DeliveryBoyRepository;
import com.example.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication
public class RestaurantApplication {

	@Autowired
	private DummyDataCreationService dummyDataCreationService;

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void insertDBData() {
		dummyDataCreationService.createData();
	}

	/**
	 *  1. Need to insert the data for restaurant and delivery boy
	 *  2. Check all the insertion api for this
	 *  3. Check all the required API in the document
	 *  4. Need to add a cron for decrease the time for delivery boy every min
	 *  5. Manage all the state in memory as per document.
	 *  6. If time is there add a test for order and delivery of order
	 *
	 */
}
