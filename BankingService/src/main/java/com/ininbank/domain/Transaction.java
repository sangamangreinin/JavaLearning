package com.ininbank.domain;

import java.time.LocalDateTime;

/**
 * Created by root on 23/3/16.
 */
public class Transaction {
    private int accountNumber;
    private String type;
    private float amount;
    private float balance;
    private String panNo;
    LocalDateTime created;

    public Transaction(int accountNumber, String type, float amount, float balance) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.balance = balance;
        this.created = LocalDateTime.now();
    }


}
