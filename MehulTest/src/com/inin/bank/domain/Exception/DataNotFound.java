package com.inin.bank.domain.Exception;

/**
 * Created by root on 23/3/16.
 */
public class DataNotFound extends RuntimeException {
    public DataNotFound(String msg){
        super(msg);
    }
}
