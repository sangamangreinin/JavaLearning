package com.inin.Bank.Exceptions;

/**
 * Created by evansbelly on 16/3/16.
 */
public class BadDataException extends RuntimeException {
    public BadDataException(String message){
        super(message);
    }
}
