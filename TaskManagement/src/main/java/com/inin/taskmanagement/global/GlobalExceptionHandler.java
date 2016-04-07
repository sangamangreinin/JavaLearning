package com.inin.taskmanagement.global;

import com.inin.taskmanagement.exception.NotAuthorizedException;
import com.inin.taskmanagement.exception.ResourceCreationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Manish Dubey on 5/4/16.
 * Handle all global Exception
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle IllegalArgumentException any where in system
     * @param e
     * @return error message and error code
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    public Error handleIllegalArgumentException(Exception e){
        return new Error(412,e.getMessage());
    }

    /**
     * Handle exception if service failed to create resource
     * @param e
     * @return error message
     */
    @ExceptionHandler(ResourceCreationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Error handleResourceCreationException(Exception e){
        return new Error(500,e.getMessage());
    }

    /**
     * Handle Unauthorized access exception
     * @param e
     * @return error message
     */
    @ExceptionHandler(NotAuthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Error handleNotAuthorizedException(Exception e){
        return new Error(401,e.getMessage());
    }

}
