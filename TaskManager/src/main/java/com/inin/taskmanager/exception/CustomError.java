package com.inin.taskmanager.exception;

/**
 * Created by virendra on 5/4/16.
 * CustomError class.
 * This class is used to display the error to the caller
 */
public class CustomError {

    /**
     * code of the error occured
     */
    final private int code;

    /**
     * message to be displayed during error
     */
    final private String message;

    /**
     * parametrized constructor accepting code and message to be displayed
     *
     * @param code    integer code
     * @param message String message
     */
    public CustomError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * gets the code of the error
     *
     * @return int code of the error
     */
    public int getCode() {
        return code;
    }

    /**
     * gets the message of the error
     *
     * @return String message of the error
     */
    public String getMessage() {
        return message;
    }
}
