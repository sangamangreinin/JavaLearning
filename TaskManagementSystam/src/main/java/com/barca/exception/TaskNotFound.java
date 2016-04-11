package com.barca.exception;

/**
 * Created by root on 11/4/16.
 */
public class TaskNotFound extends RuntimeException {
    public TaskNotFound(String msg) {
        super(msg);
    }
}
