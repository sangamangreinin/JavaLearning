package com.ininbank.exceptions;

/**
 * Created by Deepak on 23/3/16.
 * This is related to System exceptions, which occurred due to system configuration problem.
 */
public class SystemExceptions extends Exception{
    public SystemExceptions(String message) {
        super(message);
    }
}
