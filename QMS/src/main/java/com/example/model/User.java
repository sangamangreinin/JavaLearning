package com.example.model;

import java.time.LocalDateTime;

/**
 * Created by root on 12/5/16.
 */
public class User {
    int id;
    String name;
    LocalDateTime createdDate;

    public User(String name) {
        //this.id = id;
        this.name = name;
        //this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
