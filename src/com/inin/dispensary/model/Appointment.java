package com.inin.dispensary.model;

import com.inin.dispensary.logger.Log;

/**
 * Created by sangam on 28/3/16.
 * appointment class to hold date of appointment and time slot
 */
public class Appointment {
    String date;
    int startTime;
    int endTime;
    Patient patient;

    /**
     * contrustor to initialize new appointment
     * @param patient for which appoint should be created
     * @param date on which date
     * @param startTime by what time
     * @param endTime till what time
     */
    public Appointment(Patient patient, String date, int startTime, int endTime) {
        this.patient = patient;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * display appointment
     */
    public void displayAppointment() {
        Log.x("Date " + date + " Start time " + startTime + " End time " + endTime + " Patient Name " + patient.getName());
    }

    /**
     * Single responsibility function, create Appointment object
     * @param patient for which appoint should be created
     * @param date on which date
     * @param startTime by what time
     * @param endTime till what times
     * @return Appointment Object
     */
    public static Appointment create(Patient patient, String date, int startTime, int endTime) {
        return new Appointment(patient, date, startTime, endTime);
    }

    /**
     * getter method to get start time of appointment
     * @return starttime in integer
     */
    public int getStartTime() {
        return startTime;
    }
}
