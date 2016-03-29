package com.inin.domian;

/**
 * Created by root on 28/3/16.
 *
 */
public class Chemists {
    private String retailerID;
    private String name;
    private Contact contact;

    /**
     *
     * @param retailerID
     * @param name
     * @param contact
     */
    public Chemists(String retailerID, String name, Contact contact) {
        this.retailerID = retailerID;
        this.name = name;
        this.contact = contact;
    }
}
