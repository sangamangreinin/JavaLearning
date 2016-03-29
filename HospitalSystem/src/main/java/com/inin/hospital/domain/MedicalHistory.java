package com.inin.hospital.domain;

import com.inin.hospital.Helper.SEVERITY;

import java.time.LocalDateTime;

/**
 * Created by Deepak on 29/3/16.
 * This represents the medical history of patient.
 */
public class MedicalHistory{

    private String diseases;
    private String comments;
    private LocalDateTime duration;
    private SEVERITY severity;

    /**
     * This will create the medical history of patient from the following parameter.
     * @param diseases is name of diseases, from which patient is suffer.
     * @param comments is overall comments given by the doctor.
     * @param duration is time period from patient is suffer from above mentioned diseases.
     * @param severity is intensity of diseases on patient, which noted on LOW, MEDIUM and HIGH basis.*/
    public MedicalHistory(String diseases, String comments, LocalDateTime duration, SEVERITY severity) {
        this.diseases = diseases;
        this.comments = comments;
        this.duration = duration;
        this.severity = severity;
    }

    /**
     * Default medical history of patient who's medical history is not available,
     * Or he is does not suffer from any previous diseases.*/
    public MedicalHistory(){
        this("N/A", "N/A", null, null);
    }
}
