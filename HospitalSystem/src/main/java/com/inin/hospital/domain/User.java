package com.inin.hospital.domain;

import com.inin.hospital.Helper.Gender;

import java.time.LocalDateTime;

/**
 * Created by Deepak on 28/3/16.
 * This class represents the user in system.
 */
public class User {
    private static final int serialVersionUID = 1;

    static int count = 0;
    private int id;
    private String name;
    private Enum<Gender> gender;
    private LocalDateTime birthDate;
    private Contact contact;
    private LocalDateTime created = LocalDateTime.now();

    /**
     * This constructor is responsible to create User with following parameters.
     * @param name is name of user.
     * @param gender is gender of student which enum type.
     * @param birthDate is date of birth of student.
     * @param mobile is mobile number of user in system.
     * @param email is email id of user in system.
     * @param landline is landline number of user in system.
     * @param address is residential address of user.*/
    public User(String name, Enum<Gender> gender, LocalDateTime birthDate, int mobile, String email, int landline, String address){
        this.id = count++;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.contact = new Contact(mobile, email, landline, address);
    }

    /**
     * This constructor is responsible to create User with following parameters.
     * @param name is name of user.
     * @param gender is gender of student which enum type.
     * @param birthDate is date of birth of student.
     * @param mobile is mobile number of user in system.
     * @param email is email id of user in system.
     * @param address is residential address of user.*/
    public User(String name, Enum<Gender> gender, LocalDateTime birthDate, int mobile, String email, String address){
        this(name, gender, birthDate, mobile, email, 0, address);
    }

    /**
     * This constructor is responsible to create User with following parameters.
     * @param name is name of user.
     * @param gender is gender of student which enum type.
     * @param birthDate is date of birth of student.
     * @param mobile is mobile number of user in system.
     * @param landline is landline number of user in system.
     * @param address is residential address of user.*/
    public User(String name, Enum<Gender> gender, LocalDateTime birthDate, int mobile, int landline, String address){
        this(name, gender, birthDate, mobile, null, landline, address);
    }

}