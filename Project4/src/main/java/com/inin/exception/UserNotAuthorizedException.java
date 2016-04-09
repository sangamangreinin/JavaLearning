package com.inin.exception;

/**
 * Created by root on 8/4/16.
 */
public class UserNotAuthorizedException extends RuntimeException {
    public UserNotAuthorizedException(String message) {
        super(message);
    }
}
