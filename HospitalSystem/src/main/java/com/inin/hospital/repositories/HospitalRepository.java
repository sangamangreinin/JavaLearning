package com.inin.hospital.repositories;

import com.inin.hospital.domain.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Deepak on 29/3/16.
 * This responsible for maintaining the list of entity.
 */
public class HospitalRepository {
    public static List<Doctor> doctorList;
    public static List<Patient> patientList;
    public static Map<Appointment, Patient> appointmentPatientMap;
    public static List<Chemist> chemistList;
    public static Map<Patient, MedicalHistory> patientMedicalHistoryMap;
    public static Map<Patient, Priscription> patientPriscriptionMap;
}
