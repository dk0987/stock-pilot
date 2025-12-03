package com.sp.userservice.exceptions;

public class AuthoritiesNotFoundException extends RuntimeException {
    public AuthoritiesNotFoundException(String message) {
        super(message);
    }
}
