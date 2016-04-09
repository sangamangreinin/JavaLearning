package com.inin;

import org.springframework.http.HttpStatus;

/**
 * Created by evansbelly on 7/4/16.
 */
public class Response {

    private int id;
    private String message;


    public Response(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public Response(String message) {
        this.message = message;
    }
}
