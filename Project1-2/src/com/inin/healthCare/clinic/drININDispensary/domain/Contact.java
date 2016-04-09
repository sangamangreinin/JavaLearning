package com.inin.healthCare.clinic.drININDispensary.domain;

/**
 * Created by leroy on 29/3/16.
 */
public class Contact {

    private String email;

    private String address;

    private int phoneNumber;

    public Contact(String email, String address, int phNo){
        this.email = email;
        this.address = address;
        this.phoneNumber = phNo;
    }

}
