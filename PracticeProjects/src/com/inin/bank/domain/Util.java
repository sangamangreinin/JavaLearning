package com.inin.bank.domain;

import com.sun.deploy.util.StringUtils;

import java.util.List;

/**
 * Created by root on 23/3/16.
 */
public class Util {

    /**
     * check whether list is valid(not null & not empty)
     *
     * @param list
     * @return boolean
     */
    public static boolean isValidList(List list) {
        return list != null && !list.isEmpty();
    }

    /**
     * check whether String is valid(not null & not empty)
     *
     * @param str
     * @return
     */
    public static boolean isValidString(String str) {
        return str != null && !str.isEmpty();
    }
}
