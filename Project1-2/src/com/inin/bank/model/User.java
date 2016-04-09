package com.inin.bank.model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by leroy on 23/3/16.
 *
 * contains all customer related properties like name , address , phone number , kc documents
 */
public class User implements Serializable{

    private static final long serialVersionUID = 123L;

    String name;    // name of the customer

    String address; // address of the customer

    int phNo;   // phone number of the customer

    Document[] documents;   // kyc documents of the customer

    /**
     *
     * @param name name of the customer
     * @param address address of the customer
     * @param phNo phone number of the customer
     * @param documents kyc documents of the customer
     */
    public User(String name, String address, int phNo, Document[] documents) {
        this.name = name;
        this.address = address;
        this.phNo = phNo;
        this.documents = documents;
    }

    @Override
    public String toString() {
        return "\n      name : " + name +
                "\n     address='" + address +
                "\n     phNo=" + phNo +
                "\n     Document Details : " + Arrays.toString(documents);
    }
}
