package com.inin.model;

import java.time.LocalDateTime;

/**
 * Created by root on 5/4/16.
 */
public class User {
    private int id;
    private String name;
    private char gender;
    private LocalDateTime created;

    public User(String name, char gender) {
        this.name = name;
        this.gender = gender;
        this.created = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public char getGender() {
        return gender;
    }

    public LocalDateTime getCreated() {
        return created;
    }
}

