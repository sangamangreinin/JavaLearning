package com.inin.domain.model;

import com.inin.domain.model.Login;

import java.io.Serializable;

/**
 * Created by virendradubey on 28/3/16.
 */
public class Patient extends Login implements Serializable{


    private static final long serialVersionUID = 1;

    public Patient(String name, int age, char gender) {
        super(name, age, gender);
    }
}
