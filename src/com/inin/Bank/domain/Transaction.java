package com.inin.Bank.domain;

import java.time.LocalDateTime;

/**
 * Created by root on 23/3/16.
 */
public class Transaction {
    /**
     * transaction amount
     */
    float amount;
    /**
     * account number of the customers
     */
    long accountNumber;
    /**
     * date on which the transaction was made
     */
    LocalDateTime transactionDate;
    /**
     * stores the transaction type i.e withdraw or deposit
     */
    String transactionType;
    /**
     * initialize the transaction object
     * @param accountNumber
     * @param amount
     */
    private Transaction(long accountNumber, float amount, String transactionType) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.transactionType = transactionType;
        setTransactionDate();
    }

    /**
     * set the transaction date
     */
    private void setTransactionDate() {
        this.transactionDate = LocalDateTime.now();
    }

    /**
     * creates an object of class TRansaction
     * @param accountNumber
     * @param amount
     * @return a new transaction with all the necessary properties of the object initialized
     */
    public static Transaction createTransaction(long accountNumber, float amount, String transactionType){
        return new Transaction(accountNumber, amount, transactionType);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", accountNumber=" + accountNumber +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
