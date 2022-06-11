package com.culturebreathexhibitionsback.controller;

import com.culturebreathexhibitionsback.dto.AuthorizedUserDto;
import com.culturebreathexhibitionsback.dto.OrderDto;
import com.culturebreathexhibitionsback.service.AuthorizedUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authorized")
public class AuthorizedUserController {

    private final AuthorizedUserService authorizedUserService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<AuthorizedUserDto> getAllUsers() {
        return authorizedUserService.getAllAuthorizedUsers();
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public AuthorizedUserDto getAuthorizedUserById(@PathVariable UUID userId) {
        return authorizedUserService.findAuthorizedUserById(userId);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_AUTHORIZED')")
    public ResponseEntity<AuthorizedUserDto> createAuthorizedUser(@RequestBody AuthorizedUserDto userDto) {
        AuthorizedUserDto authorizedUserDto = authorizedUserService.createAuthorizedUser(userDto);
        return ResponseEntity.ok(authorizedUserDto);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasRole('ROLE_AUTHORIZED')")
    public ResponseEntity<AuthorizedUserDto> updateAuthorizedUser(@PathVariable UUID userId, @RequestBody AuthorizedUserDto userDto) {
        AuthorizedUserDto authorizedUserDto = authorizedUserService.updateAuthorizedUserById(userId, userDto);
        return ResponseEntity.ok(authorizedUserDto);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteAuthorizedUser(@PathVariable UUID userId) {
        authorizedUserService.deleteAuthorizedUserById(userId);
    }

    @GetMapping("/{userId}/orders")
    @PreAuthorize("hasRole('ROLE_AUTHORIZED')")
    public List<OrderDto> getUsersOrdersById(@PathVariable UUID userId) {
        return authorizedUserService.findUsersOrdersById(userId);
    }

    @GetMapping("/{userId}/orders/{orderId}")
    @PreAuthorize("hasRole('ROLE_AUTHORIZED')")
    public OrderDto getOrderById(@PathVariable UUID userId, @PathVariable UUID orderId) {
        return authorizedUserService.getOrderById(orderId);
    }

    @PostMapping("/{userId}/orders")
    @PreAuthorize("hasRole('ROLE_AUTHORIZED')")
    public ResponseEntity<OrderDto> createOrder(@PathVariable UUID userId, @RequestBody OrderDto orderDto) {
        OrderDto savedOrderDto = authorizedUserService.createOrder(userId, orderDto);
        return ResponseEntity.ok(savedOrderDto);
    }

    @DeleteMapping("/{userId}/orders/{orderId}/cancel")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_AUTHORIZED')")
    public void deleteOrderById(@PathVariable UUID userId, @PathVariable UUID orderId) {
        authorizedUserService.deleteOrderById(orderId);
    }

}
