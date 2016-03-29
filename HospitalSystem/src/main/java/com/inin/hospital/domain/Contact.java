package com.inin.hospital.domain;

/**
 * Created by Deepak on 28/3/16.
 * This class represents the contact details of users in system.
 */
public class Contact {
    private int mobile;
    private String email;
    private int landline;
    private String address;

    /**
     * This is responsible for creating the contact of user with following details
     * @param mobile is mobile number of user.
     * @param email is email id of user.
     * @param landline is landline number of user in system.
     * */
    public Contact(int mobile, String email, int landline, String address) {
        this.mobile = mobile;
        this.email = email;
        this.landline = landline;
        this.address = address;
    }

    /**
     * This is responsible for creating the contact of user with following details
     * @param mobile is mobile number of user.
     * @param email is email id of user.
     * */
    public Contact(int mobile, String email, String address) {
        this.email = email;
        this.mobile = mobile;
        this.address = address;
    }

    /**
     * This is responsible for creating the contact of user with following details
     * @param mobile is mobile number of user.
     * @param landline is landline number of user in system.
     * */
    public Contact(int mobile, int landline, String address) {
        this.mobile = mobile;
        this.landline = landline;
        this.address = address;
    }

}
