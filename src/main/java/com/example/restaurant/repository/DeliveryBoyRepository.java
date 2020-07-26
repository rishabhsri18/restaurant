package com.example.restaurant.repository;

import com.example.restaurant.constants.DeliveryBoyStatus;
import com.example.restaurant.entity.DeliveryBoy;
import com.example.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryBoyRepository extends JpaRepository<DeliveryBoy, Long> {
    Optional<DeliveryBoy> findByRestaurant(@Param("restaurant_id") Restaurant restaurant);

    Optional<List<DeliveryBoy>> findByDeliveryBoyStatus(DeliveryBoyStatus deliveryBoyStatus);
}
