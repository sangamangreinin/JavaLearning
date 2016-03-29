package com.inin.hospital.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;

/**
 * Created by Deepak on 29/3/16.
 * This represents the appointment of Doctor for current month only. One can not take appointment more than adavance of 30 days.
 */
public class Appointment {

    private static int count = 0;
    int appointmentNo;
    Patient patient;
    LocalDateTime dateTime;

    private Appointment() {
    }

    /**
     * This is responsible for booking an appointment in system.
     * @param patient is patient for whome appointment is book.
     * @param dateTime is date and timing of appointment of patient.*/
    public Appointment bookAppointment(Patient patient, LocalDateTime dateTime){
        Appointment appointment = null;
        if(isAppointmentAvailable(dateTime)) {

            appointment = new Appointment();
            appointment.appointmentNo = count++;
            appointment.patient = patient;
            appointment.dateTime = dateTime;
        }

        return appointment;
    }

    public boolean isAppointmentAvailable(LocalDateTime dateTime){
        Calendar calendar = Calendar.getInstance();

        return false;
    }
}
