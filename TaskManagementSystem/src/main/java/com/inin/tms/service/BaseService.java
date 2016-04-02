package com.inin.tms.service;

import java.security.InvalidParameterException;

/**
 * Created by root on 3/3/16.
 *
 */
public abstract class BaseService {
    protected void validateNotNull(Object value, String argumentName){
        if(value == null)
            throw new InvalidParameterException("The value for "+ argumentName +" is not set");
    }
}
