package com.inin.tms.service;

import java.security.InvalidParameterException;

/**
 * Created by root on 3/3/16.
 * Maintains all the common functions.
 */
public abstract class BaseService {
    /**
     * Validating the null or empty value of the field
     * @param value Actual value to validate
     * @param argumentName Argument or field name to validate
     * @throws InvalidParameterException Throws if null value passed in the argument
     */
    protected void validate(Object value, String argumentName){
        if(value == null || value == "")
            throw new InvalidParameterException("The value for "+ argumentName +" is not set");
    }

    protected boolean isValidString(String str) {
        return str != null && !str.isEmpty();
    }
}
