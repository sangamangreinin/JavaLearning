package com.inin.bank.exception;

/**
 * Created by root on 23/3/16.
 */
public class LowInitialBalanceException extends RuntimeException {
    public LowInitialBalanceException(String message) {
        super(message);
    }
}
