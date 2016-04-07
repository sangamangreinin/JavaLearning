package com.inin.tms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**

 * Created by root on 3/3/16.
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = -8618106322425896447L;

    public BadRequestException(String message) {
        super(message);
    }
}
