package com.boot.user.services.exception;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(String message){
        super(message);
    }
}
