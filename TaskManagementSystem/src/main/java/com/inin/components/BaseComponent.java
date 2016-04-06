package com.inin.components;

import com.inin.exceptions.InvalidInputException;

/**
 * Created by Deepak on 5/4/16.
 * this is base component class having common functionality to have in component class.
 */
abstract public class BaseComponent {

    /**
     * This is validation function for any object. it will take the value and key is lable of object pass to check
     * @param value is object value which to validate
     * @param key is label of object passed.*/
    public static boolean validateNull(Object value, String key){
        if(value == null || value.toString().equals(""))
            throw new InvalidInputException("Invalid value given in "+key); //return false;
        return true;
    }
}
