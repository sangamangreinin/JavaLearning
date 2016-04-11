package com.barca.util;

/**
 * Created by root on 7/4/16.
 */
public class Util {

    /**
     * check the string is valid or nor
     * @param s
     * @return true if string is valid else false
     */
    public static Boolean isInValidString(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
