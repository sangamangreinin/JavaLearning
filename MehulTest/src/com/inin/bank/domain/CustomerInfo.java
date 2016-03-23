package com.inin.bank.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by root on 23/3/16.
 */
public class CustomerInfo implements Serializable {

    private static final long serialVersionUID = 1;

    // it contains the name of the customer
    private String name;
    // it contains the address of the customer
    private String address;
    // it contains the mob no. of the customer
    private long contactNumber;

    // it contains the kyc documents of the customer
    private HashMap<String,String> kycDocs;


    @Override
    public String toString() {
        return "CustomerInfo{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contactNumber=" + contactNumber +
                ", kycDocs=" + kycDocs +
                '}';
    }

    /**\
     *
     * @param name
     * @param address
     * @param contactNumber
     */
    public CustomerInfo(String name, String address, long contactNumber) {
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
    }


    /**
     *
     * @param name
     * @param address
     * @param contactNumber
     * @param kycDocs
     */
    public CustomerInfo(String name, String address, long contactNumber,HashMap<String,String> kycDocs) {
       this(name,address,contactNumber);
        this.kycDocs = kycDocs;
    }
}
