package com.inin.bank.exception;

/**
 * Created by root on 23/3/16.
 *
 */
public class TransactionNotAllowedException extends RuntimeException {
    public TransactionNotAllowedException(String msg) { super(msg);}
}
