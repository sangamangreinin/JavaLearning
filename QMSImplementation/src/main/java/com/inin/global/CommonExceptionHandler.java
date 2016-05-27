package com.inin.global;

import com.inin.Error;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Common exception for queue and user controller, The illegal argument exception will get caught here
 */
@ControllerAdvice
public class CommonExceptionHandler {
    /**
     * Illegal argument exception will be caught here
     * @param e exception object
     * @return error response code 100
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error handleCommonIllegalArgumentExceptions(Exception e){
        return new Error(100, e.getMessage());
    }

    /**
     * HttpMessageNotReadable exception will be caught here
     * @param e exception object
     * @return error response code 102
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error handleCommonHttpMessageNotReadableExceptions(Exception e){
        return new Error(102, "Invalid argument provided");
    }

    /**
     * Data access exception will be caught here
     * @param e exception object
     * @return error response code 103
     */
    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Error handleDataAccessExceptions(Exception e){
        return new Error(103, "Some error occurred");
    }
}
