package com.inin.bank.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by leroy on 23/3/16.
 *
 * Holds the transaction related properties of Bank account
 */
public class Transaction implements Serializable{

    private static final long serialVersionUID = 789L;

    LocalDateTime transactionDate;  // time when the transaction happened

    String transactionType;  // type of transaction whether withrawn or deposited

    String transactionMode;    // cheque or cash

    double amountDeposited;     //amount deposited or withrawn

    String panNo;       // pan card no of the customer

    double balance;     // balance in the bank account

    /**
     *
     * @param transactionDate time when the transaction happened
     * @param transactionType type of transaction whether withrawn or deposited
     * @param mode cheque or cash
     * @param amountDeposited amount deposited or withrawn
     * @param balance balance in the bank account
     * @param panNo pan card no of the customers
     */
    public Transaction(LocalDateTime transactionDate, String transactionType,String mode, double amountDeposited, double balance, String panNo) {
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.transactionMode = mode;
        this.amountDeposited = amountDeposited;
        this.balance = balance;
        this.panNo = panNo;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    @Override
    public String toString() {
        return "\nTransaction on : "+ transactionDate +"\n" +
                "-------------------------" +"\n" +
                " transactionType= " + transactionType + "\n" +
                " transactionType= " + transactionMode + "\n" +
                " amountDeposited= " + amountDeposited + "\n" +
                " panNo= " + panNo + "\n" +
                " balance= " + balance + "\n" +
                "-------------------------"+ "\n";
    }
}
