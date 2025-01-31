package com.workintech.s19d1.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ActorException extends RuntimeException {
    private HttpStatus httpStatus;
    public ActorException(String message, HttpStatus httpStatus) {
        super(message);
    }
}
