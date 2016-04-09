package com.inin.exception;

/** model class used during exception ot set the custom message and error code
 * Created by root on 8/4/16.
 */
public class Error {

    /**
     * @id - integer error code
     * @message - String message
     */
    private int id;

    private String message;

    /**
     * takes 2 parameter as argument
     * @param id error code
     * @param message - string message
     */
    public Error(int id, String message) {
        this.id = id;
        this.message = message;
    }

    /**
     *
     * @return String message
     */
    public String getMessage() {
        return message;
    }
}
