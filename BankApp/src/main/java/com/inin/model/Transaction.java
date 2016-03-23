package com.inin.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Class used for transaction details
 */
public class Transaction implements Serializable{
    private static final long serialVersionUID = 1L;

    private String id;
    private String transactionType;
    private double amount;
    private String panNumber;
    private LocalDateTime transactionDate;

    public Transaction(String transactionType, double amount, String panNumber) {
        this.id = UUID.randomUUID().toString();
        this.transactionType = transactionType;
        this.amount = amount;
        this.panNumber = panNumber;
        transactionDate = LocalDateTime.now();
    }

    public String  getId() {
        return id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", panNumber='" + panNumber + '\'' +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
