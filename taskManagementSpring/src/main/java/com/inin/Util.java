package com.inin;

/**
 * Created by root on 20/4/16.
 */
public class Util {

    /**
     * check if the supplied string is invalid
     * @param attribute to be checked
     * @return true if invalid else false
     */
    public static boolean isInValidString(String attribute){
        if(attribute == null || attribute ==""){
            return true;
        }
        return false;
    }

    /**
     * check if the supplied int is invalid
     * @param attribute to be checked
     * @return true if invalid else false
     */
    public static boolean isInValidInt(int attribute){
        if(attribute <= 0){
            return true;
        }
        return false;
    }
}
