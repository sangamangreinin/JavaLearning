package com.inin.hospital.solid;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Calendar;

/**
 * Created by Deepak on 29/3/16.
 * This represents the System constant values thorough out the system.
 */
public class SystemConstant {

    public static final DayOfWeek[] WORKING_DAYS = {DayOfWeek.MONDAY,DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY, DayOfWeek.SATURDAY};

    public static final int MAX_APPOINTMENT_PER_DAY = 10;

    public static final int MAX_DAYS_BOOKING = 30;
    public static LocalDateTime UPTO_ADVANCED_BOOKING = LocalDateTime.now().plusDays(MAX_DAYS_BOOKING);

}
