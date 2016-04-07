package com.inin.taskmanager.exception;

public class RecordAlreadyExistsException extends RuntimeException {

    /**
     * constructor
     * @param message
     */
    public RecordAlreadyExistsException(final String message ) {
        super(message);
    }
}
