package com.inin.dispensaryApp.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by Manish Dubey on 28/3/16.
 * This is the base class of Doctor and Patient which hold the information common in both
 */
public class Person {
    /**
     * Person's Id
     */
    private String id;

    /**
     * Person's name
     */
    private String name;

    /**
     * Person's Address
     */
    private String address;

    /**
     * Person's Phone Number
     */
    private String phone;

    /**
     * Person's Date of Birth
     */
    private LocalDateTime dob;

    /**
     * Person's Gender
     */
    private String gender;

    /**
     * Person's Email Id
     */
    private String email;

    /**
     * Person Created Date
     */
    private LocalDateTime dateCreated;

    /**
     * Person's Modified date
     */
    private LocalDateTime dateModified;

    /**
     * Create Person object by passing basic details
     * @param name
     * @param address
     * @param dob
     * @param gender
     */
    public Person(String name, String address, LocalDateTime dob, String gender) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.gender = gender;
        this.dateCreated = this.dateModified = LocalDateTime.now();
    }

    /**
     * Create Person object by passing basic details along with phone number
     * @param name
     * @param address
     * @param phone
     * @param dob
     * @param gender
     */
    public Person(String name, String address, String phone, LocalDateTime dob, String gender) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.dateCreated = this.dateModified = LocalDateTime.now();
    }

    /**
     * Create Person object by passing basic details along with phone number and email Id
     * @param name
     * @param address
     * @param phone
     * @param dob
     * @param gender
     * @param email
     */
    public Person(String name, String address, String phone, LocalDateTime dob, String gender, String email) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.dateCreated = this.dateModified = LocalDateTime.now();
    }

    /**
     * Get the Person's Id
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Get Person's Name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the Person's Address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Get the Person's Phone Number
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Get the Person's Date of Birth
     * @return dob
     */
    public LocalDateTime getDob() {
        return dob;
    }

    /**
     * Get the Person's Gender
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Get the Person's Email id
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get the Person's Created Date
     * @return dateCreated
     */
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    /**
     * Get the Person's Modified Date
     * @return dateModified
     */
    public LocalDateTime getDateModified() {
        return dateModified;
    }
}
