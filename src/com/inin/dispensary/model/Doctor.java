package com.inin.dispensary.model;

import com.inin.dispensary.logger.Log;

import java.util.*;

/**
 * Created by sangam on 28/3/16.
 */
public class Doctor extends Authentication {

    Map<String, Patient> patients;
    Map<String, List<Appointment>> appointments;
    List<Chemist> chemists;

    public Doctor(String id, String name, String address) {
        super(id, name, address);
        appointments = new HashMap<String, List<Appointment>>();
    }

    public Patient createPatient(String id, String name, String address, String summary, List<Medicine>medicines) {
        Patient patient = Patient.create(id, name, address, summary, medicines);
        getPatients().put(id, patient);
        return patient;
    }

    public Patient updatePatient(String id, String name, String address) {
        return Patient.update(getPatients().get(id), name, address);
    }

    public Patient deletePatient() {
        return null;
    }

    public Map<String, List<Appointment>> getAppointments() {
        return appointments;
    }

    public List<Chemist> getChemists() {
        return chemists;
    }

    public static void processPrescription() {

    }

    public static Doctor create(String id, String name, String address) {
        Doctor doctor = new Doctor(id, name, address);
        return doctor;
    }

    public void displayAppointments() {
        for (Map.Entry<String, List<Appointment>> entry : getAppointments().entrySet()) {
            ArrayList<Appointment> appointments = (ArrayList<Appointment>) entry.getValue();
            for (Appointment appointment : appointments) {
                appointment.displayAppointment();
            }
        }
    }

    public Appointment bookAppointment(Patient patient, String date, int startTime, int endTime) {
        Appointment appointment = null;
        // check for available slot
        boolean slotAvailable = isAppointAvailable(date, startTime, endTime);

        if (slotAvailable) {
            appointment = Appointment.create(patient, date, startTime, endTime);
            List<Appointment> appointments = getAppointments().get(date);
            if (appointments == null) {
                // if this is first appointment of the day
                appointments = new ArrayList<Appointment>();
            }
            appointments.add(appointment);
            getAppointments().put(date, appointments);
        }
        return appointment;
    }

    private boolean isAppointAvailable(String date, int startTime, int endTime) {
        List<Appointment> appointments = getAppointments().get(date);
        if (appointments == null) {
            return true;
        } else {
            for (Appointment appointment : appointments) {
                // here we have to add logic to make sure there are no duplicate appointments
                // for now we are skipping this check
                return  true;
            }
        }
        return false;
    }

    public Map<String, Patient> getPatients() {
        return patients;
    }
}