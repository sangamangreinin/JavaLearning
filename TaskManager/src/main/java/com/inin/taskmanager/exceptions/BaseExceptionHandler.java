package com.inin.taskmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by virendra on 5/4/16.
 * BaseExceptionHandler class. This class is used to handle exceptions globally.
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public CustomError handleTaskNotFoundException(final Exception e){
        return new CustomError(404, e.getMessage());
    }
}
