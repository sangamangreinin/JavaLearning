package com.inin.healthCare.clinic.drININDispensary.domain;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by root on 29/3/16.
 */
public class Doctor{

    private HashSet<String> specialization;

    private LocalTime availabilityFrom;

    private LocalTime availabilityTo;

    private ArrayList<Chemist> chemists;

    private HashMap<Integer, Patient> patientHashMap;

    private HashSet<Appointment> appointments;

    private Login login;
}
