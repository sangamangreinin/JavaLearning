package com.inin.dispensary.model;

/**
 * base class for all human beings
 * Created by sangam on 28/3/16.
 */
public class Entity {
    private String id;
    private String name;
    private String address;

    /**
     * assign values to entity
     * @param id
     * @param name
     * @param address
     */
    public Entity(String id, String name, String address)
    {
        this.name = name;
        this.address = address;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }
}
