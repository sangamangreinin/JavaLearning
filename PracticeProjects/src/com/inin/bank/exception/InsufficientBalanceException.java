package com.inin.bank.exception;

/**
 * Created by root on 23/3/16.
 */
public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
