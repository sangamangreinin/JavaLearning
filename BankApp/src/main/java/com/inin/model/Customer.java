package com.inin.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Created by root on 23/3/16.
 */
public class Customer implements Serializable{
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String address;
    private String phoneNo;
    private Set<KYCDocument> kycDocuments;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;

    public Customer(String name, String address, String phoneNo, Set<KYCDocument> kycDocuments) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.kycDocuments = kycDocuments;
        this.dateCreated = this.dateModified = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.dateModified = LocalDateTime.now();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        this.dateModified = LocalDateTime.now();
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
        this.dateModified = LocalDateTime.now();
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

}
