package com.inin.model;

import java.time.LocalDateTime;

/**
 * Created by root on 12/5/16.
 */
public class User {
    /**
     * id of the user
     */
    int id;
    /**
     * user name
     */
    String name;
    /**
     * user created date and time
     */
    LocalDateTime createdDate;

    public User() {
    }

    /**
     * initialize the user object
     * @param name user name in string
     */
    public User(String name) {
        this.name = name;
        this.createdDate = LocalDateTime.now();
    }

    /**
     * get user id
     * @return the user id in int
     */
    public int getId() {
        return id;
    }

    /**
     * set user id
     * @param id user id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * get user name
     * @return user name in string
     */
    public String getName() {
        return name;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * set user name
     * @param name user name in string
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * get created date
     * @return user created date and time
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if(obj == null || getClass() != obj.getClass() ) return false;

        return  this.getId() == ((User) obj).getId();
    }

    @Override
    public int hashCode() {
        return this.getId();
    }
}
