package com.zakhar.server.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundExceptions extends RuntimeException{
    public EntityNotFoundExceptions(String message) {
        super(message);
    }
}
