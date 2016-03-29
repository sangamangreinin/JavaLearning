package com.inin.domain.model;

import java.io.Serializable;

/**
 * Created by virendradubey on 29/3/16.
 */
public class Login extends Person implements Serializable{

    protected Login(String name, int age, char gender) {
        super(name, age, gender);
    }

}
