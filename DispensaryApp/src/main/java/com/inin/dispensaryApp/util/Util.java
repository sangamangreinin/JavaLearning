package com.inin.dispensaryApp.util;

/**
 * Created by Manish Dubey on 29/3/16.
 */
public class Util {

    /**
     * Validate Strings again null and blank
     * @param str
     * @return boolean
     */
    public static boolean isValidString(String str){
        return str!=null && !str.isEmpty();
    }
}
