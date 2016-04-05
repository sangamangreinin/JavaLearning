package com.inin.bank.domain;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by lokesh on 23/3/16.
 * Customer Modal representing Customer Entity
 */
public class Customer implements Serializable{

    private static final int serialVersionUID = 1;

    private long customerId;
    private String name;
    private String phone;
    private KYCDocument kycDocument;

    /**
     * Constructor to Create a customer object
     * @param name Name of CustomerkycDocument.toString()
     * @param phone Phone Number of Customer
     */
    public Customer(String name, String phone){
        this(name,phone,null);
    }

    /**
     * Constructor to create Customer Object
     * @param name Name of Customer
     * @param phone Phone of Customer
     * @param kycDocument kycDocument Object of Customer
     */
    public Customer(String name, String phone,KYCDocument kycDocument) {
        this.customerId = Integer.toUnsignedLong(UUID.randomUUID().hashCode());
        this.name = name;
        this.phone = phone;
        if(kycDocument != null)
            this.kycDocument = new KYCDocument(kycDocument);
    }

    /**
     *A copy constructor
     * @param customer customer object by which new Customer Object needs to be created
     */
    public Customer(Customer customer) {
        this.customerId = customer.customerId;
        this.name = customer.name;
        this.phone = customer.phone;
        this.kycDocument = new KYCDocument(customer.kycDocument);
    }

    /**
     * String Representation of Customer
     * @return String representation of Customer Object
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer(" CustomerId:" + customerId + " Name:" + name + " phone:" + phone);
        if(kycDocument != null)
            buffer.append(kycDocument);
        return buffer.toString();
    }
}
