package com.inin.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by root on 23/3/16.
 */
public abstract class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final double MIN_BALANCE = 500;
    public static final double MIN_OPENING_BALANCE = 2000;
    private String accId;
    private double balance;
    private double interest;
    private Customer customer;
    private List<Transaction> transactions = new ArrayList<>();
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;

    public Account(double balance, Customer customer,Transaction transaction) {
        this.accId = UUID.randomUUID().toString().substring(0, 8);
        this.balance = balance;
        this.customer = customer;
        this.interest = 7.5;
        this.transactions.add(transaction);
        this.dateCreated = this.dateModified = LocalDateTime.now();
    }

    public String getAccId() {
        return accId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
        this.dateModified = LocalDateTime.now();
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
        this.dateModified = LocalDateTime.now();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        this.dateModified = LocalDateTime.now();
    }


    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    /**
     * Withdraw passed amount from account
     * @param amount Amount which is withdraw from Account
     */
    public void withdraw(double amount){
        balance = balance - amount;
    }

    /**
     * Deposit passed amount into account
     * @param amount Amount which is deposit into Account
     */
    public void deposit(double amount){
        balance = balance + amount;
    }

    /**
     * Log Transaction into account
     * @param transaction
     */
    public void addTransaction(Transaction transaction){
        this.transactions.add(transaction);
    }

    /**
     * Return all transaction done in this account
     * @return List<Transaction> List of all transaction
     */
    public List<Transaction> getAllTransaction(){
        return this.transactions;
    }
}
