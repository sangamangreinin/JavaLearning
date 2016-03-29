package com.inin.dispensaryApp.model;

import java.time.LocalDateTime;

/**
 * Created by Manish Dubey on 28/3/16.
 * Class used for represent medical in Prescription
 */
public class Medication {

    /**
     * Medicine name
     */
    private String name;
    /**
     * Strength of medicine like 500 mg
     */
    private String strength;
    /**
     * Amount of medication
     */
    private String amount;
    /**
     * Medication taken a day e.g. Two time , Once a day
     */
    private String frequency;
    /**
     * How long this medicine taken
     */
    private String dispense;
    /**
     * Created Date
     */
    private LocalDateTime dateCreated;
    /**
     * Create new Medication
     * @param name
     * @param strength
     * @param amount
     * @param frequency
     * @param dispense
     */
    public Medication(String name, String strength, String amount, String frequency, String dispense) {
        this.name = name;
        this.strength = strength;
        this.amount = amount;
        this.frequency = frequency;
        this.dispense = dispense;
        this.dateCreated = LocalDateTime.now();
    }

    /**
     * Return the medication name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Return the medication strength
     * @return strength
     */
    public String getStrength() {
        return strength;
    }

    /**
     * Return the medication amount
     * @return amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Return the medication Frequency
     * @return frequency
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     * Return the dispense of medication
     * @return
     */
    public String getDispense() {
        return dispense;
    }

    /**
     * Return the medication created date
     * @return dateCreated
     */
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }
}
