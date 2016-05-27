package com.inin.exceptions;

/**
 * Created by root on 23/5/16.
 */
public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException(String message) {
        super(message);
    }
}
