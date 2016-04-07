package com.barca;

/**
 * Created by root on 4/4/16.
 */
public class Error {

    private int code;
    private String msg;

    public Error(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
