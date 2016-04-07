package com.barca.model;

import java.util.List;

/**
 * Created by root on 3/4/16.
 */
public class User {
    //name of the  user
    private String name;
   // email of the user
    private String email;
    //id of the user
    private long id;

    public  User(){}

    /**
     *
     * @param id
     * @param name
     * @param email
     */
    public User(long id ,String name, String email) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }


    public long getId() {
        return id;
    }


}

