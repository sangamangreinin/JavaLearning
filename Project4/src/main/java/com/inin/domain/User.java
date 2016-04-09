package com.inin.domain;

/** user model object extending CreateModify super Class
 * Created by root on 6/4/16.
 */
public class User extends CreateModify{

    private int id;

    private String name;

    private char gender;

    private String email;

    private String username;

    private String password;

    /**
     * taskes 5 parameters
     * @param name String value for name of user
     * @param gender char value of user's gender like (M / F)
     * @param email - String email address of the user
     * @param username String user name
     * @param password String user password which is alphanumeric
     */
    public User(String name, char gender, String email, String username, String password) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    /**
     * gets user name
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * gets Gender of user
     * @return char
     */
    public char getGender() {
        return gender;
    }

    /**
     * gets Email address of user
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * gets username of user
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * gets Password of user
     * @return String
     */
    public String getPassword() {
        return password;
    }
}
