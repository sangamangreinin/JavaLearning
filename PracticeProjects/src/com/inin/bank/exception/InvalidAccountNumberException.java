package com.inin.bank.exception;

/**
 * Created by root on 24/3/16.
 */
public class InvalidAccountNumberException extends RuntimeException {
    public InvalidAccountNumberException(String message) {
        super(message);
    }
}
