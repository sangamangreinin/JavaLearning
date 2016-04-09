package com.inin.util;

/** Class used to send success response
 * Created by root on 7/4/16.
 */
public class Success {

    /**
     * id is the actual row Id from the Database Table
     */
    private int id;

    /**
     * message to set on success
     */
    private String message;

    /**
     * takes 2 parameters
     * @param id integer value which is the actual row Id from the Database Table
     * @param message  String message to set on success
     */
    public Success(int id, String message) {
        this.id = id;
        this.message = message;
    }

    /**
     * overloaded constructor that takes 1 parameter
     * @param message String message to set
     */
    public Success(String message) {
        this.message = message;
    }

    /**
     * gets the Id
     * @return Integer
     */
    public int getId() {
        return id;
    }

    /**
     * gets the success message
     * @return String
     */
    public String getMessage() {
        return message;
    }
}
