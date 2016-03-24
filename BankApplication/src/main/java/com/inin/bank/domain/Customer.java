package com.inin.bank.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by root on 23/3/16.
 * Customer class contains the customer information like name, address, contact details & kys documents.
 */
public class Customer implements Serializable {
    private static final  long serialVersionUID = 1L;
    private int id;
    private String name;
    private String address;
    private String contact;
    private Set<Kyc> kycDetails;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;

    /**
     * creating a new customer object
     * @param id  unique id for the customer
     * @param name name of the customer
     * @param address address  of the customer
     * @param contact contact number  of the customer
     * @param kycDetails Kyc object stores the details of the kyc document
     */
    public Customer(int id, String name, String address, String contact, Set<Kyc> kycDetails) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.kycDetails = kycDetails;
        this.dateCreated = this.dateModified = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", kycDetails=" + kycDetails +
                ", dateCreated=" + dateCreated +
                ", dateModified=" + dateModified +
                '}';
    }
}
