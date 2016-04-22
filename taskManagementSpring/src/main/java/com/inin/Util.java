package com.inin;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    /**
     * Convert LocalDateTime to string format
     * @param localDateTime
     * @return the string of date in format dd/MM/yyyy HH:mm
     */
    public static String formatLocalDateTime(LocalDateTime localDateTime){
        return localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    /**
     * Convert LocalDate to string format
     * @param localDate
     * @return the string of date in format dd/MM/yyyy
     */
    public static String formatLocalDate(LocalDate localDate){
        return localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static LocalDate getLocalDateFromString(String localDate){
        return LocalDate.parse(localDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
