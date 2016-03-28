package com.inin.dispensaryApp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manish Dubey on 28/3/16.
 * This class used to represent Patient Details
 */
public class Patient extends Person{

    /**
     * Patient admit date
     */
    private LocalDateTime admitDate;
    /**
     * Doctor Id of Client
     */
    private String docId;

    /**
     * Hold Patient Sickness Details
     */
    private List<History> medicalHistory = new ArrayList<>();

    /**
     * Patient allergies
     */
    private String [] allergies;

    /**
     * Patient Prescription details
     */
    protected List<Prescription> prescriptions = new ArrayList<>();

    /**
     * Create New Patient with basic required details
     * @param name
     * @param address
     * @param dob
     * @param gender
     * @param admitDate
     * @param allergies
     */
    public Patient(String name, String address, LocalDateTime dob, String gender, LocalDateTime admitDate,String[] allergies) {
        super(name, address, dob, gender);
        this.admitDate = admitDate;
        this.allergies = allergies;
    }

    /**
     * Create New Patient with basic required details along with Patient phone number
     * @param name
     * @param address
     * @param phone
     * @param dob
     * @param gender
     * @param admitDate
     * @param allergies
     */
    public Patient(String name, String address, String phone, LocalDateTime dob, String gender, LocalDateTime admitDate,String[] allergies) {
        super(name, address, phone, dob, gender);
        this.admitDate = admitDate;
        this.allergies = allergies;
    }

    /**
     * Create New Patient with basic required details along with Patient phone number and email ID
     * @param name
     * @param address
     * @param phone
     * @param dob
     * @param gender
     * @param email
     * @param admitDate
     * @param allergies
     */
    public Patient(String name, String address, String phone, LocalDateTime dob, String gender, String email, LocalDateTime admitDate,String[] allergies) {
        super(name, address, phone, dob, gender, email);
        this.admitDate = admitDate;
        this.allergies = allergies;
    }
}
