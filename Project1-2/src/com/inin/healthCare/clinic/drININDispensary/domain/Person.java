package com.inin.healthCare.clinic.drININDispensary.domain;

import java.time.LocalDate;

/**
 * Created by Leroy on 29/3/16.
 *
 * this is the super class containing the basic state for a person. Where a person can have a contact.
 */
abstract public class Person {

    private String name;


    private byte age;

    private LocalDate dateOfBirth;

    private char gender;

    private Contact contact;

    public Person(String name, byte age, LocalDate dob, char gender){
        this.name = name;
        this.age = age;
        this.dateOfBirth = dob;
        this.gender = gender;
    }

    public Person(String name, byte age, LocalDate dob, char gender, Contact contact){
        this(name, age, dob, gender);
        this.contact = contact;
    }
}
