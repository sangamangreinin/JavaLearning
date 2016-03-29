package com.inin.domian;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by root on 28/3/16.
 *
 */
public class Patient extends Person {
    private String patientId;
    List<MedicalHistory> medicalHistory;
    Prescription prescription;
}
