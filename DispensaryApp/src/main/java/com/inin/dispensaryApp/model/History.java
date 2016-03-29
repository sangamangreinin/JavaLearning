package com.inin.dispensaryApp.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by Manish Dubey on 28/3/16.
 * Class is used to Represent patient History details
 */
public class History {
    /**
     * History Id
     */
    private String id;
    /**
     * Present Symptoms of Patient
     */
    private String symptoms;
    /**
     * Severity of Symptoms
     */
    private String severity;
    /**
     * Created date of History
     */
    private LocalDateTime dateCreated;
    /**
     * Modified date of history
     */
    private LocalDateTime dateModified;

    /**
     * Create History with patient present symptoms and it's severity
     * @param presentSymptoms
     * @param severity
     */
    public History(String presentSymptoms, String severity) {
        this.id = UUID.randomUUID().toString().substring(0,8);
        this.symptoms = presentSymptoms;
        this.severity = severity;
        this.dateCreated = this.dateModified = LocalDateTime.now();
    }

    /**
     * Return the History Id
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * Return the Patient symptoms
     * @return String
     */
    public String getSymptoms() {
        return symptoms;
    }

    /**
     * Update symptoms of patients
     * @param symptoms
     */
    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    /**
     * Return the patient symptoms severity
     * @return String
     */
    public String getSeverity() {
        return severity;
    }

    /**
     * Update patient severity
     * @param severity
     */
    public void setSeverity(String severity) {
        this.severity = severity;
    }

    /**
     * Return the created history date
     * @return LocalDateTime
     */
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }
    /**
     * Return the modified history date
     * @return LocalDateTime
     */
    public LocalDateTime getDateModified() {
        return dateModified;
    }
}
