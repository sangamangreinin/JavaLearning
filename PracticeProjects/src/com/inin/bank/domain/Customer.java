package com.inin.bank.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by root on 23/3/16.
 */
public class Customer implements Serializable {
    private static final long serialVersionUID = 1;

    /**
     * name of the customer
     */
    private String name;
    /**
     * address of the customer
     */
    private String address;
    /**
     * mobile number of the customer
     */
    private String mobileNo;
    /**
     * list of KYC documents
     */
    private List<KycDocument> kycDocumentList;

    /**
     * create customer object
     *
     * @param name     name of the customer
     * @param address  address of the customer
     * @param mobileNo mobile number of the customer
     */
    private Customer(String name, String address, String mobileNo) {
        this(name, address, mobileNo, null);
    }

    /**
     * create customer object
     *
     * @param name            name of the customer
     * @param address         address of the customer
     * @param mobileNo        mobile number of the customer
     * @param kycDocumentList list of KYC documents
     */
    private Customer(String name, String address, String mobileNo, List<KycDocument> kycDocumentList) {
        this.name = name;
        this.address = address;
        this.mobileNo = mobileNo;
        this.kycDocumentList = kycDocumentList;
    }

    /**
     * create customer object
     *
     * @param name     name of the customer
     * @param address  address of the customer
     * @param mobileNo mobile number of the customer
     * @return Customer object
     */
    public static Customer createCustomer(String name, String address, String mobileNo) {
        return new Customer(name, address, mobileNo);
    }

    /**
     * @param name            name of the customer
     * @param address         address of the customer
     * @param mobileNo        mobile number of the customer
     * @param kycDocumentList list of KYC documents
     * @return Customer object
     */
    public static Customer createCustomer(String name, String address, String mobileNo, List<KycDocument> kycDocumentList) {
        return new Customer(name, address, mobileNo, kycDocumentList);
    }

    /**
     * to display details of customer
     * @return String
     */
    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", kycDocumentList=" + kycDocumentList +
                '}';
    }
}