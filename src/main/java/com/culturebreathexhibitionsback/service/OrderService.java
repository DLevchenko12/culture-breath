package com.culturebreathexhibitionsback.service;

import com.culturebreathexhibitionsback.dto.OrderDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    List<OrderDto> getAllUsersOrdersById(UUID userId);

    OrderDto getOrderById(UUID orderId);

    OrderDto createOrder(OrderDto orderDto);

    void deleteOrderById(UUID orderId);
}
