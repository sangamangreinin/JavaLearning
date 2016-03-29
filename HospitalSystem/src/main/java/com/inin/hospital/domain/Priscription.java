package com.inin.hospital.domain;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Created by Deepak on 28/3/16.
 */
public class Priscription {
    private int id;
    private LocalDateTime dateTime;
    private Doctor doctor;
    private Patient patient;
    private Map<String, String> medicationDetails;
}
