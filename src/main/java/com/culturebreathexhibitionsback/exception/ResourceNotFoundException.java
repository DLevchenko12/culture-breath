package com.culturebreathexhibitionsback.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Resource not found: %s";

    public ResourceNotFoundException(String resource) {
        super(String.format(MESSAGE, resource));
    }
}
