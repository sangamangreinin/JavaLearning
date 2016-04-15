package com.inin.domain;

import java.time.LocalDate;
import java.util.List;

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

    LocalDate createdDate;

    public User() {
    }

    /**
     * get created date
     * @return
     */
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public int getUserId() {
        return userId;
    }

    /**
     * initialize the user object
     * @param userId
     * @param name
     */
    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
        this.createdDate = LocalDate.now();
    }

    /**
     * get user name
     * @return
     */
    public String getName() {
        return name;
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
