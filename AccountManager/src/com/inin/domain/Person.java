package com.inin.domain;

import java.io.Serializable;

/**
 * Created by virendradubey on 23/3/16.
 * Abstract class containing common properties
 */
public abstract class Person implements Serializable{

    private static final long serialVersionUID = 1;
    /**
     * name String name of the person
     */
    protected String name;
    /**
     * address String address of the person
     */
    protected String address;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    /**
     * gets the name of the person
     * @return name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name of the person
     * @param name String name of the person
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets the address of the person
     * @return address of the person
     */
    public String getAddress() {
        return address;
    }

    /**
     * sets the address of the person
     * @param address address of the person
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * display the details of the person
     */
    public abstract void displayDetails();

}
