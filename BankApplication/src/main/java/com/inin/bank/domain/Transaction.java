package com.inin.bank.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by root on 23/3/16.
 * Transaction class to maintain the transaction on the account
 */
public class Transaction implements Serializable{
    private static final  long serialVersionUID = 1L;
    private String  id;
    private String transactionType; // withdraw / deposit
    private String transactionMode; // cheque / cash
    private String panNumber; // if required
    private double balance;
    private LocalDateTime created;

    /**
     * creating a new tracnsacion object
     * @param transactionType transaction type  of the customer
     * @param transactionMode transaction mode  of the customer
     */
    public Transaction(String transactionType, String transactionMode, double balance) {
        this.id = UUID.randomUUID().toString();
        this.transactionType = transactionType;
        this.transactionMode = transactionMode;
        this.balance = balance;
        created = LocalDateTime.now();
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transactionType='" + transactionType + '\'' +
                ", transactionMode='" + transactionMode + '\'' +
                ", panNumber='" + panNumber + '\'' +
                ", balance=" + balance +
                ", created=" + created +
                '}';
    }
}
