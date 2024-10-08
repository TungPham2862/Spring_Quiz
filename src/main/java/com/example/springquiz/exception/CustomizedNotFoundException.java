package com.example.springquiz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomizedNotFoundException extends RuntimeException {
    public CustomizedNotFoundException(String message) {
        super(message);
    }
}
