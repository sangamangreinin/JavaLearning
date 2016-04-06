package com.inin.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Deepak on 2/4/16.
 * This represents invalid input given.
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidInputException extends RuntimeException {
    private static final long serialVersionUID = -8618106322425896447L;

    public InvalidInputException(String message) {
        super(message);
    }
}
