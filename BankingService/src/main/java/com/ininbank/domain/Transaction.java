package com.ininbank.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by root on 23/3/16.
 */
public class Transaction implements Serializable {
    private int accountNumber;
    private String type;
    private float amount;
    private float balance;
    LocalDateTime created;

    /**
     * Create the object of Transaction
     * @param accountNumber is account number of transaction performed
     * @param type is type of transaction performed like deposit or withdraw.
     * @param amount is amount of transaction.
     * @param balance is remaining balance after transaction.
     * */
    public Transaction(int accountNumber, String type, float amount, float balance) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.balance = balance;
        this.created = LocalDateTime.now();
    }

    /**
     * Get the account numbere of transaction.
     * */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Get the balance of transaction.
     * */
    public float getBalance() {
        return balance;
    }

}
