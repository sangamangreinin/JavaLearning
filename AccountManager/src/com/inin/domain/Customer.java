package com.inin.domain;

import java.io.Serializable;

/**
 * Created by virendradubey on 23/3/16.
 */
public class Customer extends Person implements Serializable{


    private static final long serialVersionUID = 1;
    /**
     * contact details of the customer
     */
    private CustomerContacts contacts;

    public Customer(String name, String address) {
        super(name,address);
        this.contacts = new CustomerContacts();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name="+this.name+
                ", address="+this.address+
                ", contacts=" + contacts +
                '}';
    }

    public static Person createCustomer(String name, String address) {
        Person personObj = new Customer(name, address);
        return personObj;
    }

    public CustomerContacts getContacts() {
        return contacts;
    }

    public void setContacts(CustomerContacts contacts) {
        this.contacts = contacts;
    }

    @Override
    public void displayDetails() {

    }
}
