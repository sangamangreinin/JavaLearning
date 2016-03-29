package com.inin.hospital.domain;

/**
 * Created by Deepak on 29/3/16.
 */
public class Chemist {
    private String name;
    private Contact contact;
    private String email;

    public Chemist(String name, Contact contact, String email) {
        this.name = name;
        this.contact = contact;
        this.email = email;
    }
}
