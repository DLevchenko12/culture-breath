package com.culturebreathexhibitionsback.service.impl;

import com.culturebreathexhibitionsback.model.Role;
import com.culturebreathexhibitionsback.model.User;
import com.culturebreathexhibitionsback.repository.RoleRepository;
import com.culturebreathexhibitionsback.repository.UserRepository;
import com.culturebreathexhibitionsback.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("userServiceImpl")
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    @Override
    public User registerUser(User user) {
        return register(user, "ROLE_AUTHORIZED");
    }

    @Override
    public User registerAdmin(User admin) {
        return register(admin, "ROLE_ADMIN");
    }

    private User register(User user, String roleName) {
        Role role = roleRepository.findByName(roleName);
        user.setRole(role);

        User registerUser = userRepository.save(user);
        log.info("IN register - user: {} successfully registered", registerUser);
        return registerUser;
    }

    @Override
    public User findById(UUID id) {
        User result = userRepository.findById(id).orElse(null);
        if (result == null) {
            log.warn("IN findById - user with id: {} is not found", id);
        }
        log.info("IN findById - user: {} is found by id: {}", result, id);
        return result;
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} was delete successfully", id);
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} result found", result.size());
        return result;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not Found!");
        }
        return user;
    }
}
