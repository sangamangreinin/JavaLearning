package com.inin.domain;

import java.time.LocalDate;

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
     * @return the localdate
     */
    public LocalDate getCreatedDate() {
        return createdDate;
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
        this.createdDate = LocalDate.now();
    }

    /**
     * get user name
     * @return the string
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param id user id to set in int
     */

    public void setId(int id) {
        this.userId = id;
    }

    /**
     *
     * @param name user name to set in string
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param created user created date to set in LocalDate
     */

    public void setCreated(LocalDate created) {
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

    //Please override equals and hashcode method say you can define when two users are equal lets say if they have same name
}
