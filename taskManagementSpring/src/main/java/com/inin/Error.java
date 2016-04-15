package com.inin;

/**
 * This class consist of format for error response code to be send
 */
public class Error {

    /**
     * error code
     */
    private int code;
    /**
     * error message
     */
    private String message;

    /**
     * initialize the error object
     * @param code
     * @param message
     */
    public Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * get the error code
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * get the error messages
     * @return error message
     */
    public String getMessage() {
        return message;
    }
}
