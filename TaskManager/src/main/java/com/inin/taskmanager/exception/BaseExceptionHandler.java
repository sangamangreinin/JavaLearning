package com.inin.taskmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

/**
 * Created by virendra on 5/4/16.
 * BaseExceptionHandler class. This class is used to handle exception globally.
 */
@ControllerAdvice
public class BaseExceptionHandler {

    /**
     * handles RecordNotFoundException exception and returns user defined Error
     * @param e Exception object
     * @return Custom Error object
     */
    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public CustomError handleRecordNotFoundException(final Exception e){
        return new CustomError(404, e.getMessage());
    }

    /**
     * handles RecordNotCreatedException exception and returns user defined Error
     * @param e Exception object
     * @return Custom Error object
     */
    @ExceptionHandler(
            {
                    RecordNotCreatedException.class,
                    ClassNotFoundException.class ,
                    IOException.class
            }
    )
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CustomError handleRecordNotCreatedException(final Exception e){
        return new CustomError(500, e.getMessage());
    }

    /**
     * handles IllegalAccessException exception and returns user defined Error
     * @param e Exception object
     * @return Custom Error object
     */
    @ExceptionHandler(IllegalAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public CustomError handleIllegalAccessException(final Exception e ){
        return new CustomError(400, e.getMessage());
    }
}
