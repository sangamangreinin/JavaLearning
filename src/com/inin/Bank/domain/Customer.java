package com.inin.Bank.domain;

/**
 * Created by root on 23/3/16.
 */
public class Customer {

    /**
     * name of the customer
     */
    String name;
    /**
     * contact details of the customer
     */
    int mobileNumber;
    /**
     * address of the customer
     */
    Address address;

    /**
     * know your customer documents of customer
     */
    KycDocument[] kycDocument;

    /**
     * initialize the customer object if know your customer documents are not provided
     * @param name
     * @param mobileNumber
     * @param address
     */
    private Customer(String name, int mobileNumber, Address address) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.address = address;
    }

    /**
     * initialize the customer object if know you customer are provided by the customer
     * @param name
     * @param mobileNumber
     * @param address
     * @param kycDocument
     */
    private Customer(String name, int mobileNumber, Address address, KycDocument[] kycDocument) {
        this(name, mobileNumber, address);
        this.kycDocument = kycDocument;
    }

    /**
     * creates an object of class Customer
     * @param name
     * @param mobileNumber
     * @param address
     * @return a new customer with all the necessary properties of the object initialized
     */
    public static Customer createCustomer(String name, int mobileNumber, Address address){
        return new Customer(name, mobileNumber, address);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", address=" + address +
                '}';
    }
}
