package com.inin.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Created by root on 6/4/16.
 */
public class Util {

    /**
     * parse String to LocalDateTime format
     *
     * @param date the date in string format
     * @return
     * @throws DateTimeParseException
     */
    public static LocalDateTime parseStringToLocalDateTime(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }

    /**
     * check whether the string is valid or not
     *
     * @param str the string to be checked
     * @return boolean whether the string is valid or not
     */
    public static boolean isValidString(String str) {
        return str != null && !str.isEmpty();
    }
}
