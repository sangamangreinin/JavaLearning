package com.inin.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Created by root on 7/4/16.
 */
public class Util {

    /**
     * parses the Sql TimeStamp date to LocalDateTime
     * @param timestamp is the sql timestamp sent from MySql
     * @return
     */
    public static LocalDateTime parseSqlDateToLocalDate(Timestamp timestamp){

        return LocalDateTime.ofInstant(timestamp.toInstant(), ZoneOffset.systemDefault());
    }

    /**
     * parses the string to LocalDateTime
     * @param date - is the String Date sent by the User
     * @return - LocalDateTime
     */
    public static LocalDateTime parseToLocalDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }

    /**
     * checks for null object
     * @param obj any object to the check
     * @return true if not null and false on null
     */
    public static boolean checkObjectNull(Object obj){
       if (obj != null)
           return true;

        return false;
    }

    /**
     * checks valid string
     * @param str string to eb checked
     * @return true on valid string and false on null or empty string passed
     */
    public static boolean checkValidString(String str){
        if (str != "" && str != null)
            return true;

        return false;
    }

    /**
     * checks for valid integer
     * @param val value to check which is an integer value
     * @return true on integer greater than 0 and false on negative or 0 value
     */
    public static boolean checkValidInteger(int val){
        if (val <= 0)
            return false;

        return true;
    }
}
