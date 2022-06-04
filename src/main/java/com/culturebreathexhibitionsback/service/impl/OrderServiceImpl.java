package com.culturebreathexhibitionsback.service.impl;

import com.culturebreathexhibitionsback.dto.OrderDto;
import com.culturebreathexhibitionsback.dto.mapper.OrderMapper;
import com.culturebreathexhibitionsback.exception.ResourceNotFoundException;
import com.culturebreathexhibitionsback.model.Order;
import com.culturebreathexhibitionsback.repository.OrderRepository;
import com.culturebreathexhibitionsback.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::orderToOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(UUID orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::orderToOrderDto)
                .orElseThrow(() -> {
                    log.error("Exception was thrown in 'getOrderById' operation");
                    throw new ResourceNotFoundException("Order with id: " + orderId);
                });
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = orderRepository.save(orderMapper.orderDtoToOrder(orderDto));
        return orderMapper.orderToOrderDto(order);
    }

    @Override
    public void deleteOrderById(UUID orderId) {
        orderRepository.findById(orderId).ifPresentOrElse(orderRepository::delete,
                () -> {
                    log.error("Exception was thrown in 'deleteOrderById' operation");
                    throw new ResourceNotFoundException("Order with id: " + orderId);
                });
    }
}
