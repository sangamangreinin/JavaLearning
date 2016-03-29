package com.inin.dispensaryApp.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by Manish Dubey on 28/3/16.
 * This class is used for holding the Doctor's prescription details
 */
public class Prescription {
    /**
     * Prescription Id
     */
    private String id;
    /**
     * Medicine given by Doctor
     */
    private List<Medication> medications;
    /**
     * Prescription Details
     */
    private String details;
    /**
     * Strength of medicine like 500 mg
     */
    private LocalDateTime dateCreated;

    /**
     * Created Prescription with medication details
     * @param medications
     * @param details
     */
    public Prescription(List<Medication> medications, String details) {
        this.id = UUID.randomUUID().toString().substring(0,8);
        this.medications = medications;
        this.details = details;
        this.dateCreated = LocalDateTime.now();
    }

    /**
     * Return all medication added into prescription
     * @return medications
     */
    public List<Medication> getMedications() {
        return medications;
    }

    /**
     * Add medication into the prescription
     * @param medication
     * @return boolean
     */
    public boolean addMedication(Medication medication){
        if(medication == null)
            throw new IllegalArgumentException("Medication should not be null");
        return medications.add(medication);
    }

    /**
     * Remove medication from the prescription
     * @param name
     * @return Medication
     */
    public Medication removeMedication(String name){
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("Medication name should not be null or empty");
        Medication medication = null;
        for (Medication medication1 : medications){
            if(medication1.getName().equals(name))
            {
                medication = medication1;
                medications.remove(medication);
            }
        }
        return medication;
    }

    /**
     * Return the prescription Id
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Return details about prescription
     * @return details
     */
    public String getDetails() {
        return details;
    }

    /**
     * Return Created date of prescription
     * @return dateCreated
     */
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }
}
