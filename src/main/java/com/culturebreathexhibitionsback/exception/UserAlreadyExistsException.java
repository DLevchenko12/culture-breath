package com.culturebreathexhibitionsback.exception;

import java.util.UUID;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(UUID userId) {
        super("User with id: " + userId + " already exists");
    }
}
