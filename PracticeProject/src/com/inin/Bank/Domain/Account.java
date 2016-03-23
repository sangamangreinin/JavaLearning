package com.inin.Bank.Domain;

import com.inin.Bank.Exceptions.BadDataException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by evansbelly on 23/3/16.
 */
public class Account implements Serializable {

    private long accountNo;
    private double balance;
    private static double interestRate = 2.5;
    private LocalDateTime created;
    private Customer customer;
    private ArrayList<Transaction> transactions;
    private static final int serialVersionUID = 1;

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * setting the account object
     *
     * @param accountNo
     * @param balance
     * @param customer
     * @param interestRate
     */
    public Account(long accountNo, double balance, Customer customer, double interestRate) {
        if (accountNo <= 0) {
            throw new IllegalArgumentException("Invalid account no.");
        } else if (isBalanceLow(balance)) {
            throw new BadDataException("Balance amount entered is below Rs. 2000/-");
        }
        this.accountNo = accountNo;
        this.balance = balance;
        this.customer = customer;
        this.interestRate = interestRate;
        this.created = LocalDateTime.now();
        this.transactions = new ArrayList<>();
    }

    /**
     * Function to create account that the user calls explicitly
     *
     * @param accountNo
     * @param balance
     * @param customer
     * @return Account object
     */
    public static Account createAccount(long accountNo, double balance, Customer customer) {
        Account account = new Account(accountNo, balance, customer, interestRate);
        return account;
    }

    /**
     * check for balance low below 2000
     *
     * @param balance
     * @return true if balance is below 2000 & false if balance is above 2000
     */
    public boolean isBalanceLow(double balance) {
        if (balance < 2000) {
            return true;
        } else
            return false;
    }

    /**
     * @param type   1 : withdrawal & 2 : Deposit
     * @param amount
     * @return the final balance after transaction
     */
    public double performTransaction(int type, double amount) {
        if (type == 1) {
            double tempBal = this.balance - amount;
            if (tempBal < 500) {
                System.out.println("You can't do this process. Minimum balance required is Rs. 500/-");
            } else {
                this.balance = tempBal;
                transactions.add(new Transaction("withdrawal", amount));
            }
        } else if (type == 2) {
            if (amount > 50000) {
                System.out.println("PAN card details required");
            } else {
                transactions.add(new Transaction("deposit", amount));
                this.balance += amount;
            }
        }
        return this.balance;
    }

    @Override
    public String toString() {
        return  "accountNo = " + accountNo +
                ", balance = " + balance +
                ", created = " + created +
                ", customer = " + customer +
                ", transactions = " + transactions;
    }
}
