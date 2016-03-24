package com.inin.bank.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by root on 23/3/16.
 */
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1;

    /**
     * the transactionn type
     */
    private TransactionType transactionType;
    /**
     * amount for transaction
     */
    private double amount;
    /**
     * pan number for the transaction
     */
    private String panNumber;
    /**
     * cheque number for the transaction
     */
    private int chequeNo;
    /**
     * the transaction date
     */
    private LocalDateTime transactionDate;

    /**
     * create transaction object
     *
     * @param transactionType transaction type
     * @param amount          amount for transaction
     */
    private Transaction(TransactionType transactionType, double amount) {
        this(transactionType, amount, 0, null);
    }

    /**
     * create transaction object
     *
     * @param transactionType transaction type
     * @param amount          amount for transaction
     * @param chequeNo        cheque number for transaction
     */
    private Transaction(TransactionType transactionType, double amount, int chequeNo) {
        this(transactionType, amount, chequeNo, null);
    }

    /**
     * create transaction object
     *
     * @param transactionType transaction type
     * @param amount          amount for transaction
     * @param panNumber       pan number for transaction
     */
    private Transaction(TransactionType transactionType, double amount, String panNumber) {
        this(transactionType, amount, 0, panNumber);
    }

    /**
     * create transaction object
     *
     * @param transactionType transaction type
     * @param amount          amount for transaction
     * @param chequeNo        cheque number for transaction
     * @param panNumber       pan number for transaction
     */
    private Transaction(TransactionType transactionType, double amount, int chequeNo, String panNumber) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.chequeNo = chequeNo;
        this.panNumber = panNumber;
        this.transactionDate = LocalDateTime.now();
    }

    /**
     * create transaction object
     *
     * @param transactionType transaction type
     * @param amount          amount for transaction
     * @return Transaction object
     */
    public static Transaction createTransaction(TransactionType transactionType, double amount) {
        return new Transaction(transactionType, amount);
    }

    /**
     * create transaction object
     *
     * @param transactionType transaction type
     * @param amount          amount for transaction
     * @param chequeNo        cheque number for transaction
     * @return Transaction object
     */
    public static Transaction createTransaction(TransactionType transactionType, double amount, int chequeNo) {
        return new Transaction(transactionType, amount, chequeNo);
    }

    /**
     * create transaction object
     *
     * @param transactionType transaction type
     * @param amount          amount for transaction
     * @param panNumber       pan number for transaction
     * @return Transaction object
     */
    public static Transaction createTransaction(TransactionType transactionType, double amount, String panNumber) {
        return new Transaction(transactionType, amount, panNumber);
    }

    /**
     * create transaction object
     *
     * @param transactionType transaction type
     * @param amount          amount for transaction
     * @param chequeNo        cheque number for transaction
     * @param panNumber       pan number for transaction
     * @return Transaction object
     */
    public static Transaction createTransaction(TransactionType transactionType, double amount, int chequeNo, String panNumber) {
        return new Transaction(transactionType, amount, chequeNo, panNumber);
    }

    /**
     * to display transaction details
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Transaction{" +
                "transactionType=" + transactionType +
                ", amount=" + amount +
                ", panNumber='" + (panNumber == null ? "NA" : panNumber) + '\'' +
                ", chequeNo=" + (chequeNo == 0 ? "N/A" : chequeNo) +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
