package com.inin.dispensaryApp.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Manish Dubey on 28/3/16.
 * This class represent Doctor derived from Person
 */
public class Doctor extends Person{
    /**
     * Doctor's Speciality
     */
    private String speciality;

    /**
     * Hold Doctor's patients Details
     */
    private Map<String, Patient> patients = new HashMap<>();

    /**
     * Hold Doctor's Retailers Details
     */
    private Map<String, Retailers> retailers = new HashMap<>();

    /**
     * Hold Doctor's appointment details
     */
    private Map<String, Appointment> appointments = new HashMap<>();

    /**
     * Create Doctor with basic details
     * @param name
     * @param address
     * @param dob
     * @param gender
     * @param speciality
     */
    public Doctor(String name, String address, LocalDateTime dob, String gender, String speciality) {
        super(name, address, dob, gender);
        this.speciality = speciality;
    }

    /**
     * Create Doctor with basic details along with phone number
     * @param name
     * @param address
     * @param phone
     * @param dob
     * @param gender
     * @param speciality
     */
    public Doctor(String name, String address, String phone, LocalDateTime dob, String gender, String speciality) {
        super(name, address, phone, dob, gender);
        this.speciality = speciality;
    }

    /**
     * Create Doctor with basic details along with phone number and email Id
     * @param name
     * @param address
     * @param phone
     * @param dob
     * @param gender
     * @param email
     * @param speciality
     */
    public Doctor(String name, String address, String phone, LocalDateTime dob, String gender, String email, String speciality) {
        super(name, address, phone, dob, gender, email);
        this.speciality = speciality;
    }

    /**
     * Get the Doctor Speciality Details
     * @return
     */
    public String getSpeciality() {
        return speciality;
    }
}
