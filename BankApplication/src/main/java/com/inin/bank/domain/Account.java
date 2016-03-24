package com.inin.bank.domain;

import com.inin.bank.util.Util;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 23/3/16.
 * Account class which performs user actions like deposit / withdraw the money from the account.
 */
public class Account implements Serializable{
    private static final long serialVersionUID = 1L;

    public long accountNumber;
    private double balance;
    private double interest;
    private Customer customer;
    private List<Transaction> transaction = new ArrayList<Transaction>();
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
    /**
     * Accocunt object
     * @param balance initial balance
     * @param customer customer object
     * @param transaction transaction details
     */
    public Account(double balance, Customer customer, Transaction transaction) {
        this.accountNumber = getAutoAccountNumber();
        this.balance = balance;
        this.interest = Contants.INTEREST;
        this.customer = customer;
        this.transaction.add(transaction);
        this.dateCreated = this.dateModified = LocalDateTime.now();
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * Adding trasanction into the account
     * @param transaction Transaction object to add into the account
    */
    public void addTransaction(Transaction transaction){
        this.transaction.add(transaction);
    }

    /**
     * get all transaction of the account
     * @return List of transactions
     */
    public List<Transaction> getTransactions() {
        return transaction;
    }

    /**
     * Depositing amount into the customers account
     * @param amount Amount to deposit
     */
    public void deposit(double amount){
        balance += amount;
        this.dateModified = LocalDateTime.now();
    }

    /**
     * Withdrawing amount from the customers account
     * @param amount Amount to withdraw
     */
    public void withdraw(double amount){
        balance -= amount;
        this.dateModified = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", dateCreated=" + dateCreated +
                '}';
    }

    /**
     * generating unique the account number automatically
     * @return account number
     */
    private static long getAutoAccountNumber(){
        return Util.getAccountNumber();
    }
}
