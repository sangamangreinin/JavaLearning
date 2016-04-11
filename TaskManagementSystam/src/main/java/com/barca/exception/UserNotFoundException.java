package com.barca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by root on 5/4/16.
 */
@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String msg) {
        super(msg);
    }
}
