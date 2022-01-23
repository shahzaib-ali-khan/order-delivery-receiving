package com.gorillas.order.delivery.receiving.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gorillas.order.delivery.receiving.model.Delivery;
import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, String>{
    Optional<Delivery> findById(String deliveryId);
    List<Delivery> findByisReceivedTrue();
}