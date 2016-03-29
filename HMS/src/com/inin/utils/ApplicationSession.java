package com.inin.utils;

import com.inin.domain.model.Person;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by virendradubey on 28/3/16.
 */
public class ApplicationSession {

    /**
     * isset flag
     */
    private boolean isset = false;

    /**
     * instance of ApplicationSession class
     */
    private static ApplicationSession _instance;

    /**
     * provides the session object for use in application
     * @return
     */
    public static ApplicationSession getInstance(){
        if (! (_instance instanceof ApplicationSession))
            _instance = new ApplicationSession();
        return _instance;

    }
    /**
     * checks for the session validity
     * @return
     */
    public boolean isSessionValid(){
        return isset;
    }

    /**
     * sets the session of the user using the system
     * @param obj
     * @return
     */
    public boolean setSession(Person obj){
        Map<String, Object> session = new HashMap<>();
        return false;
    }


}
