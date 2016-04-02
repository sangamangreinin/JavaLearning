package com.inin.tms.domain;

/**
 * Created by root on 2/4/16.
 * Defines the behaviour of the User class
 */
public class User {
    /**
     *
     */
    private String id;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;

    /**
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param gender
     * @param email
     */
    public User(String id, String firstName, String lastName, String gender, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }
}
