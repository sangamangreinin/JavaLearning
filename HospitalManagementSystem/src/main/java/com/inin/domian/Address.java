package com.inin.domian;

/**
 * Created by root on 29/3/16.
 *
 */
public class Address {
    private String buildingName;
    private String roomNumber;
    private String area;
    private String location;
    private String street;
    private String landmark;
    private String pincode;

    /**
     * Creates  new Address object
     * @param buildingName
     * @param roomNumber
     * @param area
     * @param location
     * @param street
     * @param landmark
     * @param pincode
     */
    public Address(String buildingName, String roomNumber, String area, String location, String street, String landmark, String pincode) {
        this.buildingName = buildingName;
        this.roomNumber = roomNumber;
        this.area = area;
        this.location = location;
        this.street = street;
        this.landmark = landmark;
        this.pincode = pincode;
    }
}
