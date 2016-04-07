package com.barca.global;

import com.barca.Error;
import com.barca.exception.UserIdAlreadyExistException;
import com.barca.exception.UserNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by root on 4/4/16.
 */

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error handleCommonExceptionHandling(Exception e) {
        return new Error(102, e.getMessage());
    }

    @ExceptionHandler(UserIdAlreadyExistException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    public Error handleUserIdAlreadyExistException(Exception e) {
        return new Error(100, e.getMessage());
    }



    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    public Error handleEmptyResultDataAccessException(Exception e) {
        return new Error(101, e.getMessage());
    }

}
