package com.inin.tms.util;

import java.util.Arrays;
import java.util.List;

/**
 * Created by root on 9/4/16
 * .
 */
public class Util {
    /**
     * Checks the status
     * @param status Task status
     * @return true if valid status else false
     */
    public static boolean isValidTaskStatus(String status){
        List<String> list = Arrays.asList("draft","assigned","completed");
        return list.contains(status);
    }
}
