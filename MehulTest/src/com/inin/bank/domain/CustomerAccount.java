package com.inin.bank.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by root on 23/3/16.
 */
public class CustomerAccount implements Serializable {

    private static final long serialVersionUID = 1;

    //it consist account Number
    private int accNo;

    // it consist balance
    private double balance;

    // it contains the all Customer Details
    private CustomerInfo customerInfo;

    //it consist interest rate for account
    private static final float INT_RATE = 2.0f;
    //it consist interest rate for account
    private static final float MIN_BAL = 500.0f;

    private static final float INITIAL_BAL = 2000.0f;

    //it consist TRANSACTION LIMIT for PAN card
    public static final float TRANSACTION_LIMIT_FOR_PAN = 50000;


    // stores the date and time when the account was created
    private LocalDateTime dateCrated;

    // it contains the all Transaction Details
    private ArrayList<Transaction> transactions;


    /**
     * @param accNo
     * @param customerInfo
     */
    public CustomerAccount(int accNo, CustomerInfo customerInfo) {
        transactions = new ArrayList<>();

        if (accNo < 1)
            throw new IllegalArgumentException("Acc no should not be less then one");

        this.accNo = accNo;
        this.balance = CustomerAccount.INITIAL_BAL;
        this.customerInfo = customerInfo;
        this.dateCrated = LocalDateTime.now();

        //Transaction transaction = new Transaction("deposit",CustomerAccount.INITIAL_BAL);
        this.transactions.add(new Transaction("deposit", CustomerAccount.INITIAL_BAL, "cash"));
    }

    /**
     * @param accNo  account Number
     * @param customerInfo  customer Info
     * @param transactions transaction object
     */
    public CustomerAccount(int accNo, CustomerInfo customerInfo, ArrayList<Transaction> transactions) {
        this(accNo, customerInfo);
        this.transactions = transactions;
    }


    public int getAccNo() {
        return accNo;
    }

    public void setAccNo(int accNo) {
                                                                                                                                                                        this.accNo = accNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public LocalDateTime getDateCrated() {
        return dateCrated;
    }

    public void setDateCrated(LocalDateTime dateCrated) {
        this.dateCrated = dateCrated;
    }


    @Override
    public String toString() {
        return "CustomerAccount{" +
                "accNo=" + accNo +
                ", balance=" + balance +
                ", customerInfo=" + customerInfo +
                ", dateCrated=" + dateCrated +
                ", transactions=" + transactions +
                '}';
    }


}
