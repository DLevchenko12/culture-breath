package com.culturebreathexhibitionsback.service.impl;

import com.culturebreathexhibitionsback.dto.AuthorizedUserDto;
import com.culturebreathexhibitionsback.dto.OrderDto;
import com.culturebreathexhibitionsback.dto.mapper.AuthorizedUserMapper;
import com.culturebreathexhibitionsback.dto.mapper.OrderMapper;
import com.culturebreathexhibitionsback.exception.ResourceNotFoundException;
import com.culturebreathexhibitionsback.exception.UserAlreadyExistsException;
import com.culturebreathexhibitionsback.model.AuthorizedUser;
import com.culturebreathexhibitionsback.repository.AuthorizedUserRepository;
import com.culturebreathexhibitionsback.repository.OrderRepository;
import com.culturebreathexhibitionsback.service.AuthorizedUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorizedUserServiceImpl implements AuthorizedUserService {

    private final AuthorizedUserMapper userMapper;
    private final AuthorizedUserRepository userRepository;
    private final OrderServiceImpl orderService;


    @Override
    public List<AuthorizedUserDto> getAllAuthorizedUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::authorizedUserToAuthorizedUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorizedUserDto findAuthorizedUserById(UUID userId) {
        return userRepository.findById(userId)
                .map(userMapper::authorizedUserToAuthorizedUserDto)
                .orElseThrow(() -> {
                    log.error("Exception was thrown during 'findAuthorizedUserById' operation");
                    throw new ResourceNotFoundException("User with id: " + userId);
                });
    }

    @Override
    public AuthorizedUserDto createAuthorizedUser(AuthorizedUserDto authorizedUserDto) {
        AuthorizedUser authorizedUser = userRepository.save(userMapper.authorizedUserDtoToAuthorizedUser(authorizedUserDto));
        return userMapper.authorizedUserToAuthorizedUserDto(authorizedUser);
    }

    @Override
    public AuthorizedUserDto updateAuthorizedUserById(UUID userId, AuthorizedUserDto authorizedUserDto) {
        AuthorizedUser authorizedUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User with id: " + userId));
        userMapper.updateAuthorizedUserFromDto(authorizedUserDto, authorizedUser);
        userRepository.save(authorizedUser);

        return userMapper.authorizedUserToAuthorizedUserDto(authorizedUser);
    }

    @Override
    public void deleteAuthorizedUserById(UUID userId) {
        userRepository.findById(userId).ifPresentOrElse(userRepository::delete, () -> {
            log.error("Exception thrown while deleting resource");
            throw new ResourceNotFoundException("User with id: " + userId);
        });
    }

    @Override
    public List<OrderDto> findUsersOrdersById(UUID userId) {
        if (!userRepository.existsById(userId)) {
            log.error("Exception was thrown");
            throw new ResourceNotFoundException("User with id: " + userId);
        } else {
            return orderService.getAllUsersOrdersById(userId);
        }
    }

    @Override
    public OrderDto getOrderById(UUID orderId) {
        return orderService.getOrderById(orderId);
    }

    @Override
    public OrderDto createOrder(UUID userId, OrderDto orderDto) {
        if (!userRepository.existsById(userId)) {
            log.error("Exception was thrown");
            throw new ResourceNotFoundException("User with id: " + userId);
        }
        return orderService.createOrder(orderDto);
    }

    @Override
    public void deleteOrderById(UUID orderId) {
        orderService.deleteOrderById(orderId);
    }
}
