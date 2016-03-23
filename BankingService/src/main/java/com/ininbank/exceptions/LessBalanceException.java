package com.ininbank.exceptions;

/**
 * Created by Deepak on 23/3/16.
 * This represents exceptions related to less balance.
 */
public class LessBalanceException extends IllegalArgumentException {
    public LessBalanceException(String message) {
        super(message);
    }
}
