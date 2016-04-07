package com.inin.tms.domain;

import java.io.Serializable;

/**
 * Created by root on 2/4/16.
 * Defines the User
 */
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * A unique id of the user
     */
    private String id;
    /**
     * First name of the user
     */
    private String firstName;
    /**
     * Laste name of the user
     */
    private String lastName;
    /**
     * gender of the user
     */
    private String gender;
    /**
     * email id  of the user
     */
    private String email;

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }
}
