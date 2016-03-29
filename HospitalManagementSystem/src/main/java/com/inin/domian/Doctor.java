package com.inin.domian;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

/**
 * Created by root on 28/3/16.
 *
 */
public class Doctor extends Person{
    private String doctorID;
    private String specilization;
    private Set<Patient> patients;
    private Map<Patient, Prescription> priscriptionMap;
    long charges;

    /**
     *
     * @param userName
     * @param password
     * @param name
     * @param gender
     * @param birthDate
     * @param mobileNo
     * @param emailID
     * @param landLine
     * @param address
     * @param specilization
     * @param charges
     */
    public Doctor(String userName, String password, String name, char gender,
                  LocalDateTime birthDate, int mobileNo, String emailID, int landLine, Address address,
                  String specilization, double charges) {

        super();
    }
}
