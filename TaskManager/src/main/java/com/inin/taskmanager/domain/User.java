package com.inin.taskmanager.domain;

import com.inin.taskmanager.domain.base.BaseDomain;
import com.inin.taskmanager.util.Util;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by virendra on 1/4/16.
 * User class. This class is used to represent the user in the application
 * This can also be termed as caller and assignee
 */
public class User extends BaseDomain implements Serializable {

    /**
     * table name
     */
    public static final String TABLE_NAME = "users";
    private static final long serialVersionUID = 1L;
    /**
     * user id
     */
    private long userId;

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

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
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
    public Long getUserId() {
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
     * inner class implementing Builder pattern to create User Object
     */
    public static class Builder {

        /**
         * user id
         */
        private long userId;

        /**
         * name of the user
         */
        private String name;
        /**
         * address of the user
         */
        private String address;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;

        public Builder(String name) {
            this.name = name;
        }

        public Builder setCreatedDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder setModifiedDate(LocalDateTime modifiedDate) {
            this.modifiedDate = modifiedDate;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setUserId(long userId) {
            this.userId = userId;
            return this;
        }

        public User create() {
            return new User(this);
        }

    }
}
