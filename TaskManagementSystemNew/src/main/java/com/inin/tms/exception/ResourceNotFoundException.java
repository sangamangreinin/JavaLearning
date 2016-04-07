package com.inin.tms.exception;

/**
 * Created by root on 5/4/16.
 *
 */
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
