package com.ininbank.domain;

import java.io.Serializable;

/**
 * Created by root on 23/3/16.
 */
public class Document implements Serializable {
    private static final long serialVersionUID = 1234567894;
    private String path;
    private String type;

    public Document(String path, String type) {
        this.path = path;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Document{" +
                "path='" + path + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
