package com.inin.hospital.domain;

import com.inin.hospital.Helper.Gender;
import com.inin.hospital.Helper.Specilization;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

/**
 * Created by Deepak on 28/3/16.
 * This class represent doctor in system
 */
public class Doctor extends SystemUser {

    private Enum<Specilization> specilization;
    double charges;
    private Set<Patient> patients;
    private Map<Patient, Priscription> priscriptionMap;
    private DayOfWeek[] workingDays;

    /**
     * This is responsible to create doctor with the following params
     * @param username is username of Doctor
     * @param password is password of doctor.
     * @param name is name of Doctor.
     * @param gender is gender of doctor.
     * @param birthDate is date of birth of doctor.
     * @param mobile is mobile number of doctor.
     * @param email is email of doctor.
     * @param landline number of doctor.
     * @param address is address of doctor.
     * @param specilization is doctor's specialization for which he is going to work.
     * @param charges is consulting charges of doctor*/
    public Doctor(String username, String password, String name, Enum<Gender> gender,
                  LocalDateTime birthDate, int mobile, String email, int landline, String address,
                  Enum<Specilization> specilization, double charges, DayOfWeek[] workingDays) {

        super(username, password, name, gender, birthDate, mobile, email, landline, address);
        this.specilization = specilization;
        this.charges = charges;
        this.workingDays = workingDays;
    }

    /**
     * This is responsible to create doctor with the following params
     * @param username is username of Doctor
     * @param password is password of doctor.
     * @param name is name of Doctor.
     * @param gender is gender of doctor.
     * @param birthDate is date of birth of doctor.
     * @param mobile is mobile number of doctor.
     * @param landline number of doctor.
     * @param address is address of doctor.
     * @param specilization is doctor's specialization for which he is going to work.
     * @param charges is consulting charges of doctor*/
    public Doctor(String username, String password, String name, Enum<Gender> gender,
                  LocalDateTime birthDate, int mobile, int landline, String address,
                  Enum<Specilization> specilization, double charges, DayOfWeek[] workingDays) {

        this(username, password, name, gender, birthDate, mobile, null,
                landline, address, specilization, charges, workingDays);
    }

    /**
     * This is responsible to create doctor with the following params
     * @param username is username of Doctor
     * @param password is password of doctor.
     * @param name is name of Doctor.
     * @param gender is gender of doctor.
     * @param birthDate is date of birth of doctor.
     * @param mobile is mobile number of doctor.
     * @param email is email of doctor.
     * @param address is address of doctor.
     * @param specilization is doctor's specialization for which he is going to work.
     * @param charges is consulting charges of doctor*/
    public Doctor(String username, String password, String name, Enum<Gender> gender,
                  LocalDateTime birthDate, int mobile, String email, String address,
                  Enum<Specilization> specilization, double charges, DayOfWeek[] workingDays) {

        this(username, password, name, gender, birthDate, mobile, email,
                0, address, specilization, charges, workingDays);
    }
}
