package com.inin.domian;

/**
 * Created by root on 28/3/16.
 *
 */
public class Contact {
    public long mobileNo;
    public String landLine;
    public String emailId;
    Address address;

    /**
     * Creates a new Contact object
     * @param mobileNo
     * @param landLine
     * @param emailId
     * @param address  Address Object contains physical address of the person.
     */
    public Contact(long mobileNo, String landLine, String emailId, Address address){
        this.mobileNo = mobileNo;
        this.landLine = landLine;
        this.emailId = emailId;
        this.address = address;
    }

    /**
     *
     * @param mobileNo
     * @param emailId
     * @param address
     */
    public Contact(long mobileNo, String emailId, Address address) {
        this.mobileNo = mobileNo;
        this.emailId = emailId;
        this.address = address;
    }

    /**
     *
     * @param landLine
     * @param emailId
     * @param address
     */
    public Contact(String landLine, String emailId, Address address) {
        this.landLine = landLine;
        this.emailId = emailId;
        this.address = address;
    }
}
