package com.inin.taskmanager.exceptions;

/**
 * Created by virendradubey on 5/4/16.
 */
public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(final String m){
        super(m);
    }
}
