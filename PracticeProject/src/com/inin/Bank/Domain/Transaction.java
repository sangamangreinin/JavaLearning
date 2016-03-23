package com.inin.Bank.Domain;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by evansbelly on 23/3/16.
 */
public class Transaction implements Serializable {

    private String type;
    private double amount;
    private static final int serialVersionUID = 1;

    /**
     * Transaction object. Used only within the account object
     * @param type
     * @param amount
     */
    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "type = '" + type + '\'' +
                ", amount = " + amount;
    }
}
