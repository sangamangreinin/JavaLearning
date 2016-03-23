package com.inin.domain;

import java.io.Serializable;

/**
 * Created by virendradubey on 23/3/16.
 */
public class CustomerContacts implements Serializable {

    private static final long serialVersionUID = 1;
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


}
