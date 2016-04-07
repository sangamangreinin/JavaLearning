package com.inin.taskmanagement.domain;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.inin.taskmanagement.constant.Gender;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Manish Dubey on 5/4/16.
 * Represent user in system
 */
public class User extends BaseDomain{
    /**
     * Id of user
     */
    private long id;
    /**
     * User name
     */
    private String name;
    /**
     * User address
     */
    private String address;
    /**
     * User phone
     */
    private String phone;
    /**
     * User Email
     */
    private String email;
    /**
     * User gender
     */
    private Gender gender;
    /**
     * User Date of birth
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfBirth;

    /**
     * No args constructor
     */
    public User() {
    }

    /**
     * Create new User
     * @param name
     * @param address
     * @param phone
     * @param email
     * @param gender
     * @param dateOfBirth
     */
    public User(String name, String address, String phone, String email, Gender gender, LocalDate dateOfBirth) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public User(long id, String name, String address, String phone, String email, Gender gender, LocalDate dateOfBirth, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    /**
     * Get the user id
     * @return user id
     */
    public long getId() {
        return id;
    }

    /**
     * Get the user name
     * @return user name
     */
    public String getName() {
        return name;
    }

    /**
     * Address of the user
     * @return address of the user
     */
    public String getAddress() {
        return address;
    }

    /**
     * Get user's phone number
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * User Email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get the user gender
     * @return user gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Get the user's date of birth
     * @return user dob
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
