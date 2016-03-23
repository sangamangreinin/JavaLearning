package com.inin.Bank.Domain;

import java.io.Serializable;

/**
 * Created by evansbelly on 23/3/16.
 */
public class Customer implements Serializable {

    private String name;
    private String address;
    private long contactNo;
    private KycInfo kycDoc;
    private static final int serialVersionUID = 1;

    /**
     * Setting the Customer object
     * @param name
     * @param address
     * @param contactNo
     * @param kycDoc
     */
    public Customer(String name, String address, long contactNo, KycInfo kycDoc) {
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
        this.kycDoc = kycDoc;
    }

    /**
     * setting customer object
     * @param name
     * @param address
     * @param contactNo
     */
    public Customer(String name, String address, long contactNo) {
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
    }

    /**
     * create user
     * @param name
     * @param address
     * @param contactNo
     * @return created customer object
     */
    public static Customer createUser(String name, String address, long contactNo) {
        Customer customer = new Customer(name, address, contactNo);
        isCustomerBlank(customer);
        return customer;
    }

    /**
     * create user
     * @param name
     * @param address
     * @param contactNo
     * @param kycDoc
     * @return created customer object
     */
    public static Customer createUser(String name, String address, long contactNo, KycInfo kycDoc) {
        Customer customer = new Customer(name, address, contactNo, kycDoc);
        isCustomerBlank(customer);
        return customer;
    }

    /**
     * blank/null check for customer object
     * @param customer
     * @throws NullPointerException Empty customer value
     */
    static void isCustomerBlank(Customer customer) {
        if (customer.equals(null)) {
            throw new NullPointerException("Empty customer information");
        }
    }

    @Override
    public String toString() {
        return "name = '" + name + '\'' +
                ", address = '" + address + '\'' +
                ", contactNo = " + contactNo +
                ", kycDoc = " + kycDoc;
    }
}
