package com.inin.domian;

import java.time.LocalDateTime;

/**
 * Created by root on 28/3/16.
 *
 */
public class Person {
    public String name;
    public char gender;
    private LocalDateTime birthDate;
    private Contact contact;
    private LocalDateTime created;

    /**
     *
     * @param name
     * @param gender
     * @param birthDate
     * @param contact
     * @param created
     */
    public Person(String name, char gender, LocalDateTime birthDate, Contact contact, LocalDateTime created) {
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.contact = contact;
        this.created = created;
    }

    public Person() {}
}
