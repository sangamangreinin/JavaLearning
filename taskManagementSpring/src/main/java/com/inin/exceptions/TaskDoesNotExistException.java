package com.inin.exceptions;

/**
 * This class consist of User defined exception if the task does not exist
 */

public class TaskDoesNotExistException extends RuntimeException{
    public TaskDoesNotExistException(String message) {
        super(message);
    }
}
