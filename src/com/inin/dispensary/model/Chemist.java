package com.inin.dispensary.model;

/**
 * handle all retailers
 * Created by sangam on 28/3/16.
 */

public class Chemist extends Entity {
    private String email;

    /**
     * assign values to new chemist
     * @param id
     * @param name
     * @param address
     */
    public Chemist(String id, String name, String address) {
        super(id, name, address);
        this.email = email;
    }
}
