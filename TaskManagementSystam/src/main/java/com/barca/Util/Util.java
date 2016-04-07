package com.barca.Util;

import org.springframework.stereotype.Service;

/**
 * Created by root on 7/4/16.
 */
public class Util {

    public static Boolean isInValidString(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
