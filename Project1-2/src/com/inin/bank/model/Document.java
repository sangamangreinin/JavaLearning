package com.inin.bank.model;

import java.io.Serializable;

/**
 * Created by leroy on 23/3/16.
 *
 * contains all documents that a customer will upload like the Pan card , Aadhar card , passport
 */
public class Document implements Serializable{

    private static final long serialVersionUID = 159L;

    String type;    // type of document like Pan card , Aadhar card , passport

    String path; // physical path of the document

    /**
     *
     * @param type type of document like Pan card , Aadhar card , passport
     * @param path physical path of the document like "/root/Desktop/Documents/aadhaar.jpg"
     */
    public Document(String type, String path) {
        this.type = type;
        this.path = path;
    }

    @Override
    public String toString() {
        return "\n            type : " + type +
                "\n          path : " + path;
    }
}
