package com.inin.exceptions;

/**
 * Created by root on 19/5/16.
 */
public class QueueDoesNotExistException extends RuntimeException{

    public QueueDoesNotExistException(String message) {
        super(message);
    }
}
