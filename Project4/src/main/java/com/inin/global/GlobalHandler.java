package com.inin.global;

import com.inin.exception.OnCreateException;
import com.inin.exception.UserNotAuthorizedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.inin.exception.Error;
/**
 * Created by root on 8/4/16.
 */
@ControllerAdvice
public class GlobalHandler {
    /**
     * user to handle exception on creation
     * @param e
     * @return error message
     */
    @ExceptionHandler(OnCreateException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Error creationException(Exception e){
        return new Error(500,e.getMessage());
    }

    /**
     *
     * @param e
     * @return error message and error code
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    public Error illegalArgumentException(Exception e){
        return new Error(412,e.getMessage());
    }

    /**
     * If user id sent was wrong while authorization this exception is thrown
     * @param e
     * @return error message
     */
    @ExceptionHandler(UserNotAuthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Error notAuthorizedException(Exception e){
        return new Error(401,e.getMessage());
    }

    /**
     * If user id sent was wrong while authorization this exception is thrown
     * @param e
     * @return error message
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Error dataIntegrityViolationException(Exception e){
        return new Error(500,e.getMessage());
    }

}