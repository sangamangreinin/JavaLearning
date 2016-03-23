package com.ininbank.domain;

import java.io.Serializable;

/**
 * Created by root on 23/3/16.
 */
public class Document implements Serializable {
    private String path;
    private String type;

    public Document(String path, String type) {
        this.path = path;
        this.type = type;
    }

}
