package com.inin.bank.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by root on 23/3/16.
 */
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1;

    // it contains Transaction type
    private String transactionType;

    // it contains Transaction amount
    private double amount;

    // it contains Transaction date and Time
    private LocalDateTime date;

    // it contains Transaction deposit via cash or cheque
    private String depositType;

    // it contains Transaction pan card number if transaction is greater then 50k
    private String panCard;

    /**
     * @param transactionType
     * @param amount
     */
    public Transaction(String transactionType, double amount) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.date = LocalDateTime.now();
    }

    /**
     * @param transactionType
     * @param amount
     * @param depositType
     * @param panCard
     */

    public Transaction(String transactionType, double amount, String depositType, String panCard) {
        this(transactionType, amount, depositType);
        this.panCard = panCard;
    }

    /**\
     *
     * @param transactionType
     * @param amount
     * @param depositType
     */
    public Transaction(String transactionType, double amount, String depositType) {
        this(transactionType, amount);
        this.depositType = depositType;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDepositType() {
        return depositType;
    }

    public void setDepositType(String depositType) {
        this.depositType = depositType;
    }

    public String getPanCard() {
        return panCard;
    }

    public void setPanCard(String panCard) {
        this.panCard = panCard;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", depositType='" + depositType + '\'' +
                ", panCard='" + panCard + '\'' +
                '}';
    }
}
