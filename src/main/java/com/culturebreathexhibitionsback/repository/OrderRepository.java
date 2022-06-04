package com.culturebreathexhibitionsback.repository;

import com.culturebreathexhibitionsback.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findUserOrdersById(UUID userId);
}