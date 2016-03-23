package com.inin.bank.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by lokesh on 23/3/16.
 * Transaction Modal to represent Transaction on Account
 */
public class Transaction implements Serializable {
    private static final int serialVersionUID = 1;

    private long transactionId;
//    private long accountNo;
    private Enum<TransactionType> transactionType;
    private double amount;
    private long chequeNumber;
    private String panNumber;
    private LocalDateTime dateCreated;

    /**
     * Constructor to create Transaction object
     * @param transactionType Type of Transaction
     * @param amount Amount of Transaction
     * @param chequeNumber Cheque Number of Transaction, if not a cash transaction
     * @param panNumber panNumber if Deposit Transaction >= ₹5000/-
     */
    public Transaction(Enum<TransactionType> transactionType, double amount, long chequeNumber, String panNumber) {
        this.transactionId = Integer.toUnsignedLong(UUID.randomUUID().hashCode());
        this.transactionType = transactionType;
        this.amount = amount;
        this.chequeNumber = chequeNumber;
        this.panNumber = panNumber;
        this.dateCreated = LocalDateTime.now();
    }

    /**
     * Constructor to create Transaction
     * @param transactionType Type of Transaction
     * @param amount Amount of Transaction
     */
    public Transaction(Enum<TransactionType> transactionType, double amount) {
        this(transactionType,amount,0,null);
    }

    /**
     * Constructor to create Transaction
     * @param transactionType Transaction Type
     * @param amount Amount of Transaction
     * @param chequeNumber Cheque Number for Transaction
     */
    public Transaction(Enum<TransactionType> transactionType, double amount, long chequeNumber) {
        this(transactionType,amount,chequeNumber,null);
    }

    /**
     * String Representation of Transaction
     * @return String of Transaction
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer(" TransactionId:" + transactionId + " TransactionType:" + transactionType + " amount:" + amount);
        if(chequeNumber != 0)
            buffer.append(" ChequeNumber:" + chequeNumber);
        if(panNumber != null)
            buffer.append(" PanNumber:" + panNumber);
        buffer.append(" DateOfTransaction:" + dateCreated);
        return buffer.toString();
    }
}
