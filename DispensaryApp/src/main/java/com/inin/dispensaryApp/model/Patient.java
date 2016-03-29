package com.inin.dispensaryApp.model;

import com.inin.dispensaryApp.util.Util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
     * Hold Patient Sickness Details
     */
    private Map<String,History> medicalHistory = new HashMap<>();

    /**
     * Patient allergies
     */
    private List<String> allergies = new ArrayList<>();

    /**
     * Patient Prescription details
     */
    protected Map<String,Prescription> prescriptions = new HashMap<>();

    /**
     * Create New Patient with basic required details
     * @param name
     * @param address
     * @param dob
     * @param gender
     * @param admitDate
     */
    public Patient(String name, String address, LocalDateTime dob, String gender, LocalDateTime admitDate) {
        super(name, address, dob, gender);
        this.admitDate = admitDate;
    }

    /**
     * Create New Patient with basic required details along with Patient phone number
     * @param name
     * @param address
     * @param phone
     * @param dob
     * @param gender
     * @param admitDate
     */
    public Patient(String name, String address, String phone, LocalDateTime dob, String gender, LocalDateTime admitDate) {
        super(name, address, phone, dob, gender);
        this.admitDate = admitDate;
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
     */
    public Patient(String name, String address, String phone, LocalDateTime dob, String gender, String email, LocalDateTime admitDate) {
        super(name, address, phone, dob, gender, email);
        this.admitDate = admitDate;
    }

    /**
     * Return the Patient admitted date
     * @return LocalDateTime
     */
    public LocalDateTime getAdmitDate() {
        return admitDate;
    }

    /**
     * Return the medical history of the Patient
     * @return List<History>
     */
    public List<History> getMedicalHistory() {
        return medicalHistory.values().stream().collect(Collectors.toList());
    }

    /**
     * Return the patient allergies
     * @return  List<String>
     */
    public List<String> getAllergies() {
        return allergies;
    }

    /**
     * Return the patient Prescription
     * @return List<Prescription>
     */
    public List<Prescription> getPrescriptions() {
        return prescriptions.values().stream().collect(Collectors.toList());
    }

    /**
     * Add medical History of patient
     * @return boolean
     */
    public void addMedicalHistory(History history){
        if(history == null)
            throw new IllegalArgumentException("History should not be null");

        medicalHistory.put(history.getId(),history);
    }

    /**
     * Update medical History of patient
     * @return boolean
     */
    public History updateMedicalHistory(String id,Map<String,String> historyData){

        if(id == null || id.isEmpty())
            throw new IllegalArgumentException("Id should not be null or empty");

        if (!medicalHistory.containsKey(id))
            throw new IllegalArgumentException("Invalid Id provided");
        History history = medicalHistory.get(id);
        if(Util.isValidString(historyData.get("symptoms")))
            history.setSymptoms(historyData.get("symptoms"));
        if(Util.isValidString(historyData.get("severity")))
            history.setSeverity(historyData.get("severity"));
        return history;
    }

    /**
     * Remove medical History of patient
     * @return History
     */
    public History romoveMedicalHistory(String id){
        if(id == null || id.isEmpty())
            throw new IllegalArgumentException("Id should not be null or empty");

        if (!medicalHistory.containsKey(id))
            throw new IllegalArgumentException("Invalid Id provided");

        return medicalHistory.remove(id);
    }

    /**
     * Add medical History of patient
     * @return boolean
     */
    public void addPrescription(Prescription prescription){
        if(prescription == null)
            throw new IllegalArgumentException("Prescription should not be null");

        prescriptions.put(prescription.getId(),prescription);
    }

    /**
     * Update medical History of patient
     * @return boolean
     */
    public Prescription updatePrescription(String id,Map<String,Object> prescriptionData){

        if(id == null || id.isEmpty())
            throw new IllegalArgumentException("Id should not be null or empty");

        if (!prescriptions.containsKey(id))
            throw new IllegalArgumentException("Invalid Id provided");
        Prescription prescription = prescriptions.get(id);
        return prescription;
    }

    /**
     * Remove medical History of patient
     * @return History
     */
    public Prescription romovePrescription(String id){
        if(id == null || id.isEmpty())
            throw new IllegalArgumentException("Id should not be null or empty");

        if (!prescriptions.containsKey(id))
            throw new IllegalArgumentException("Invalid Id provided");

        return prescriptions.remove(id);
    }



}
