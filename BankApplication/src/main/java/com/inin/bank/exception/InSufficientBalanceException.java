package com.inin.bank.exception;

/**
 * Created by root on 23/3/16.
 *
 */
public class InSufficientBalanceException extends RuntimeException{
    public InSufficientBalanceException(String msg) {super(msg);}
}
