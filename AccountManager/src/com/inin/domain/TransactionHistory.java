package com.inin.domain;

import com.inin.constants.DepositMode;
import com.inin.constants.TransactionType;
import com.inin.constants.WithdrawalMode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by virendradubey on 23/3/16.
 */
public class TransactionHistory implements Serializable{

    private static final long serialVersionUID = 1;
    private LocalDateTime time = LocalDateTime.now();
    private String transactionType;
    private String depositMode;
    private String withdrawMode;
    private double amount = 0;
    private double balance = 0;


    public TransactionHistory(TransactionHistoryBuilder transactionHistoryBuilder) {
        this.transactionType = transactionHistoryBuilder.transactionType;
        this.depositMode = transactionHistoryBuilder.depositMode;
        this.withdrawMode = transactionHistoryBuilder.withdrawMode;
        this.amount = transactionHistoryBuilder.amount;
        this.balance = transactionHistoryBuilder.balance;
    }

    public double getBalance() {
        return balance;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getDepositMode() {
        return depositMode;
    }

    public String getWithdrawMode() {
        return withdrawMode;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "TransactionHistory{" +
                "time=" + time +
                "|\t transactionType='" + transactionType + '\'' +
                "|\t depositMode='" + depositMode + '\'' +
                "|\t withdrawMode='" + withdrawMode + '\'' +
                "|\t amount=" + amount +
                '}';
    }



    /**
     * Inner class implementing Builder pattern to record transaction history
     */

    public static class TransactionHistoryBuilder implements Serializable{

        public String transactionType = null;
        public String depositMode = null;
        public String withdrawMode = null;
        public double amount = 0;
        public double balance = 0;


        public TransactionHistoryBuilder forType(TransactionType type){
            this.transactionType = type.toString();
            return this;
        }

        public TransactionHistoryBuilder withAmount(double amount){
            this.amount = amount;
            return this;
        }

        public TransactionHistoryBuilder withMode(DepositMode mode){
            this.depositMode = mode.toString();
            return this;
        }

        public TransactionHistoryBuilder withMode(WithdrawalMode mode){
            this.withdrawMode = mode.toString();
            return this;
        }

        public TransactionHistoryBuilder hasTotal(double balance){
            this.balance = balance;
            return this;
        }

        public TransactionHistory create(){
            return new TransactionHistory(this);
        }
    }
}
