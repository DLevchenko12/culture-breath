package com.culturebreathexhibitionsback.service;

import com.culturebreathexhibitionsback.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface UserService extends UserDetailsService {

    User registerUser(User user);

    User registerAdmin(User admin);

    User findById(UUID id);

    void delete(UUID id);

    List<User> getAll();
}
