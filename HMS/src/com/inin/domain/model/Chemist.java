package com.inin.domain.model;

import java.io.Serializable;

/**
 * Created by virendradubey on 29/3/16.
 */
public class Chemist extends Person implements Serializable{

    private static final long serialVersionUID = 1;


    /**
     * private constructor. used to create the chemist object
     * @param name
     * @param age
     * @param gender
     */
    private Chemist(String name, int age, char gender) {
        super(name, age, gender);
    }

    /**
     * creates the new chemist object and returns it for use in the system
     * @param name name of the chemist
     * @param age age of the chemist owner
     * @param gender gender of the chemist owner
     * @return Chemist object
     */
    public static Chemist createChemist(String name, int age, char gender){
        Chemist obj = new Chemist(name,age,gender);
        return obj;
    }

}
