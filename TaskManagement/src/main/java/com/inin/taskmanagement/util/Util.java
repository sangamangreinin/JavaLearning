package com.inin.taskmanagement.util;

/**
 * Created by Manish Dubey on 5/4/16.
 * Utility function used across the function
 */
public class Util {
    /**
     * Validate string against null and empty
     * @param str
     * @return true if passed string is neither null or empty else false
     */
    public static boolean isValidString(String str){
        return str != null && !str.isEmpty();
    }

}
