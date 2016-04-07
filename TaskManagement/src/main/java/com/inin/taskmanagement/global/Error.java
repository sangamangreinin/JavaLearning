package com.inin.taskmanagement.global;

/**
 * Created by Manish Dubey on 5/4/16.
 * Represent Error class
 */
public class Error {

    /**
     * Error code
     */
    private int code;
    /**
     * Error message
     */
    private String message;

    /**
     * Create new Error object with code and error message
     * @param code
     * @param message
     */
    public Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Get set error code
     * @return error code
     */
    public int getCode() {
        return code;
    }

    /**
     * Get set error message
     * @return message
     */
    public String getMessage() {
        return message;
    }
}
