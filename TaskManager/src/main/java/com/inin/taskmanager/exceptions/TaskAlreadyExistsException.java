package com.inin.taskmanager.exceptions;

public class TaskAlreadyExistsException extends RuntimeException {

    public TaskAlreadyExistsException(final String message ) {
        super(message);
    }
}
