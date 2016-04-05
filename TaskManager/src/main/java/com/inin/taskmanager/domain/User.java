package com.inin.taskmanager.domain;

import com.inin.taskmanager.domain.base.BaseDomain;
import com.inin.taskmanager.utils.Util;

import java.io.Serializable;

import org.joda.time.LocalDateTime;

/**
 * Created by virendra on 1/4/16.
 * User class. This class is used to represent the user in the application
 * This can also be termed as caller and assignee
 */
public class User extends BaseDomain implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * table name
     */
    public static final String TABLE_NAME = "users";

    /**
     * user id
     */
    private String userId;

    /**
     * name of the user
     */
    private String name;
    /**
     * address of the user
     */
    private String address;

    public User() {
    }

    public User(Builder builder) {
        this.name = builder.name;
        this.userId = builder.userId;
        this.address = builder.address;
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }

    /**
     * gets the address of the user
     *
     * @return String address
     */
    public String getAddress() {
        return address;
    }

    /**
     * gets the user id
     *
     * @return String user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * gets the name of the user
     *
     * @return String user name
     */
    public String getName() {
        return name;
    }

    /**
     * saves the new user entry
     */
    public void save() {

        userId = Util.getMasterUserId();
        createdDate = LocalDateTime.now();
        modifiedDate = LocalDateTime.now();
    }

    /**
     * updates details of existing user entry
     */
    public void update() {
        modifiedDate = LocalDateTime.now();
    }

    public static class Builder {

        /**
         * user id
         */
        private String userId;

        /**
         * name of the user
         */
        private String name;
        /**
         * address of the user
         */
        private String address;


        public Builder(String name) {
            this.name = name;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public User create() {
            return new User(this);
        }

    }
}
