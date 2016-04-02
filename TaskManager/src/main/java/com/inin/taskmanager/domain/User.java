package com.inin.taskmanager.domain;

import com.inin.taskmanager.domain.base.BaseDomain;

import java.io.Serializable;

/**
 * Created by virendra on 1/4/16.
 * User class. This class is used to represent the user in the application
 * This can also be termed as caller and assignee
 */
public class User extends BaseDomain implements Serializable {

    private static final long serialVersionUID = 1L;

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
}
