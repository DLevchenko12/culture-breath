package com.culturebreathexhibitionsback.repository;

import com.culturebreathexhibitionsback.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Query(value = "select orders from AuthorizedUser where id = :id")
    List<Order> findAllByUserId(UUID id);
}