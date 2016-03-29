package com.inin.domain.model;

import java.io.Serializable;

/**
 * Created by virendradubey on 28/3/16.
 * Doctor class is used to represent the doctors in the application
 */
public class Doctor extends Login implements Serializable{

    public Doctor(String name, int age, char gender) {
        super(name, age, gender);
    }


}
