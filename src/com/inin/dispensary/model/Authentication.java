package com.inin.dispensary.model;

/**
 * Created by sangam on 28/3/16.
 * Get username and password. Check with database where credentials are ok.
 */
public abstract class Authentication extends Entity{
    private String username;
    private String password;

    public Authentication(String id, String name, String address) {
        super(id, name, address);
    }

    /**
     * validate username and password
     * @param username Username of the system user
     * @param password Password of system user
     * @return true if successful validation
     */
    public boolean login(String username, String password)
    {
        return true;
    }

    /**
     * Logout logged in user from system
     * @return true if successful logout
     */
    public boolean logout()
    {
        return true;
    }
}
