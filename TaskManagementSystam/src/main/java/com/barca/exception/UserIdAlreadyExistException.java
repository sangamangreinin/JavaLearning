package com.barca.exception;

/**
 * Created by root on 4/4/16.
 */
public class UserIdAlreadyExistException extends RuntimeException {

    public UserIdAlreadyExistException(String msg){
        super(msg);
    }
}
