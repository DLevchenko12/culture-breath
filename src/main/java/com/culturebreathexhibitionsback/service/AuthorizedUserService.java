package com.culturebreathexhibitionsback.service;

import com.culturebreathexhibitionsback.dto.AuthorizedUserDto;
import com.culturebreathexhibitionsback.dto.OrderDto;

import java.util.List;
import java.util.UUID;

public interface AuthorizedUserService {

    List<AuthorizedUserDto> getAllAuthorizedUsers();

    AuthorizedUserDto findAuthorizedUserById(UUID userId);

    AuthorizedUserDto createAuthorizedUser(AuthorizedUserDto authorizedUserDto);

    AuthorizedUserDto updateAuthorizedUserById(UUID userId, AuthorizedUserDto authorizedUserDto);

    void deleteAuthorizedUserById(UUID userId);

    List<OrderDto> findAllUsersOrdersByUserId(UUID userId);

    OrderDto createOrder(OrderDto orderDto);

    void deleteOrderById(UUID orderId);
}

