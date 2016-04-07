package com.inin.taskmanagement.exception;

/**
 * Created by root on 6/4/16.
 */
public class NotAuthorizedException extends RuntimeException {
    public NotAuthorizedException(String message) {
        super(message);
    }
}
