package com.ininbank.domain;

/**
 * Created by root on 23/3/16.
 */
public class Person {
    private static final long serialVersionUID = 1234567895;
    private String name;
    private String address;
    private int contact;

    public Person(String name, String address, int contact) {
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact=" + contact +
                '}';
    }
}
