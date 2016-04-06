package com.inin.domain;

import com.inin.utility.Utility;

/**
 * Created by Deepak on 2/4/16.
 * This is domain model class represents User in system.
 */
public class SystemUser {
    private static final long serialVersionUID = -8618106322425896447L;

    /** This is unique id of User.*/
    private int id;

    /** This is name of user in system. */
    private String name;

    /** This is email of user in system. */
    private String email;

    /** This is username of user in system, which can be used to logged in in system.*/
    private String username;

    /** This is password of user in system, using which one can successfully logged in. */
    private String password;


    public SystemUser(){
        this.id = Utility.generateUserId();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SystemUser that = (SystemUser) o;

        if (id != that.id) return false;
        if (!name.equals(that.name)) return false;
        if (!email.equals(that.email)) return false;
        if (!username.equals(that.username)) return false;
        return password.equals(that.password);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
