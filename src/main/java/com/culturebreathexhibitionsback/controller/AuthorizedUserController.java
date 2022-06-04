package com.culturebreathexhibitionsback.controller;

import com.culturebreathexhibitionsback.dto.AuthorizedUserDto;
import com.culturebreathexhibitionsback.service.AuthorizedUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public List<AuthorizedUserDto> getAllUsers() {
        return authorizedUserService.getAllAuthorizedUsers();
    }

    @PostMapping
    public ResponseEntity<AuthorizedUserDto> createAuthorizedUser(@RequestBody AuthorizedUserDto userDto) {
        AuthorizedUserDto authorizedUserDto = authorizedUserService.createAuthorizedUser(userDto);
        return ResponseEntity.ok(authorizedUserDto);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<AuthorizedUserDto> updateAuthorizedUser(@PathVariable UUID userId, @RequestBody AuthorizedUserDto userDto) {
        AuthorizedUserDto authorizedUserDto = authorizedUserService.updateAuthorizedUserById(userId, userDto);
        return ResponseEntity.ok(authorizedUserDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteAuthorizedUser(@PathVariable UUID userId) {
        authorizedUserService.deleteAuthorizedUserById(userId);
    }


}
