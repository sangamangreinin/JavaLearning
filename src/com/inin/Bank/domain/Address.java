package com.inin.Bank.domain;

/**
 * Created by root on 23/3/16.
 */
public class Address {
    /**
     * state of customer
     */
    String state;
    /**
     * city of customer
     */
    String city;
    /**
     * residential address of customer
     */
    String residentialAddress;
    /**
     * pincode of the customer
     */
    int pinCode;

    /**
     * initialize the address object
     * @param state
     * @param city
     * @param residentialAddress
     * @param pinCode
     */
    private Address(String state, String city, String residentialAddress, int pinCode) {
        this.state = state;
        this.city = city;
        this.residentialAddress = residentialAddress;
        this.pinCode = pinCode;
    }

    /**
     * creates an object of class Address
     * @param state
     * @param city
     * @param residentialAddress
     * @param pinCode
     * @return a new address with all the necessary properties of the object initialized
     */
    public static Address createAddress(String state, String city, String residentialAddress, int pinCode){
        return new Address(state, city, residentialAddress, pinCode);
    }


    @Override
    public String toString() {
        return "Address{" +
                "state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", residentialAddress='" + residentialAddress + '\'' +
                ", pincode=" + pinCode +
                '}';
    }
}
