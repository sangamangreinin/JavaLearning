package com.ininbank.domain;

import java.io.Serializable;
import java.time.Period;

/**
 * Created by Deepak on 23/3/16.
 * This represents Customer in the system.
 */
public class Customer extends Person implements Serializable{

    private static final long serialVersionUID = 1234567893;
    private int id;

    /**
     * Initialize  the customer with the details.
     * @param name is name of customer in system
     * @param address is address of customer in system
     * @param contact is contact number of customer in system
     * */
    public Customer(String name, String address, int contact) {
        super(name, address, contact);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                "} " + super.toString();
    }
}
