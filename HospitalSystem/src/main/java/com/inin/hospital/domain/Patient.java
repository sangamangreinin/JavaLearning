package com.inin.hospital.domain;

import com.inin.hospital.Helper.Gender;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Deepak on 28/3/16.
 * This class represents the patient in system
 */
public class Patient extends SystemUser {

    List<MedicalHistory> medicalHistories;

    /**
     * This is resoponsible to create a patient with following details.
     * @param username is username of Patient
     * @param password is password of Patient.
     * @param name is name of Patient.
     * @param gender is gender of Patient.
     * @param birthDate is date of birth of Patient.
     * @param mobile is mobile number of Patient.
     * @param email is email of Patient.
     * @param landline number of Patient.
     * @param address is address of Patient.
     * @param medicalHistories is previous history of patient, about his diseases. */
    public Patient(String username, String password, String name, Enum<Gender> gender,
                   LocalDateTime birthDate, int mobile, String email,
                   int landline, String address, List<MedicalHistory> medicalHistories) {

        super(username, password, name, gender, birthDate, mobile, email, landline, address);
        this.medicalHistories = medicalHistories;
    }

    /**
     * This is resoponsible to create a patient with following details.
     * @param username is username of Patient
     * @param password is password of Patient.
     * @param name is name of Patient.
     * @param gender is gender of Patient.
     * @param birthDate is date of birth of Patient.
     * @param mobile is mobile number of Patient.
     * @param email is email of Patient.
     * @param address is address of Patient.
     * @param medicalHistories is previous history of patient, about his diseases. */
    public Patient(String username, String password, String name, Enum<Gender> gender,
                   LocalDateTime birthDate, int mobile, String email,
                   String address, List<MedicalHistory> medicalHistories) {

        this(username, password, name, gender, birthDate, mobile, email,
                0, address, medicalHistories);
    }

    /**
     * This is resoponsible to create a patient with following details.
     * @param username is username of Patient
     * @param password is password of Patient.
     * @param name is name of Patient.
     * @param gender is gender of Patient.
     * @param birthDate is date of birth of Patient.
     * @param mobile is mobile number of Patient.
     * @param landline number of Patient.
     * @param address is address of Patient.
     * @param medicalHistories is previous history of patient, about his diseases. */
    public Patient(String username, String password, String name, Enum<Gender> gender,
                   LocalDateTime birthDate, int mobile, int landline,
                   String address, List<MedicalHistory> medicalHistories) {

        this(username, password, name, gender, birthDate, mobile, null,
                landline, address, medicalHistories);
    }

}
