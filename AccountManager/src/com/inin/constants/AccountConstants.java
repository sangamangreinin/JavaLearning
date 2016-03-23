package com.inin.constants;

import java.io.Serializable;

/**
 * Created by virendradubey on 23/3/16.
 */
public enum AccountConstants implements Serializable{
    MINIMUM_ACCOUNT_OPENING_BALANCE(2000),
    MINIMUM_MAINTAIN_BALANCE(500),
    MINIMUM_AMOUNT_WITHOUT_PAN(49999.99);

    private final double value;

    AccountConstants(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

}
