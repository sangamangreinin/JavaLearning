package com.inin.model;

import com.inin.Util;

import java.time.LocalDateTime;

/**
 * Model class for User
 */

public class User {
    /**
     * id of the user
     */
    int userId;
    /**
     * user name
     */
    String name;
    /**
     * user created date
     */

    LocalDateTime createdDate;

    public User() {
    }

    /**
     * get created date
     * @return the created date in string format
     */
    public String getCreatedDate() {
        return Util.formatLocalDateTime(createdDate);
    }

    public int getUserId() {
        return userId;
    }

    /**
     * initialize the user object
     * @param name
     */
    public User(String name) {
        this.name = name;
        this.createdDate = LocalDateTime.now();
    }

    /**
     * get user name
     * @return the string
     */
    public String getName() {
        return name;
    }

    /**
     * set user id
     * @param id user id to set in int
     */

    public void setId(int id) {
        this.userId = id;
    }

    /**
     * set user name
     * @param name user name to set in string
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * set created date
     * @param created user created date to set in LocalDate
     */

    public void setCreated(LocalDateTime created) {
        this.createdDate = created;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
