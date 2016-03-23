package bank.domain;

import bank.util.Util;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 23/3/16.
 * Account class which performs user actions like deposit / withdraw the money from the account.
 */
public class Account implements Serializable{
    private static final  long serialVersionUID = 1;

    public long accountNumber;
    private double balance;
    LocalDateTime dateCreated;
    Customer customer;
    private List<Transaction> transaction;

    /**
     * Accocunt object
     * @param balance initial balance
     * @param customer customer object
     * @param transaction transaction details
     */
    public Account(double balance, Customer customer, ArrayList<Transaction> transaction) {
        this.accountNumber = getAutoAccountNumber();
        this.balance = balance;
        this.dateCreated = LocalDateTime.now();
        this.customer = customer;
        this.transaction = transaction;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }

    public List<Transaction> getTransaction() {
        return transaction;
    }

    /**
     * Depositing amount into the customers account
     * @param amount
     * @return
     */
    public double deposit(double amount){
        balance += amount;
        return balance;
        // update date here
    }

    /**
     * Withdrawing amount from the customers account
     * @param amount
     * @return
     */
    public double withdraw(double amount){
        balance -= amount;
        return balance;
        // update date here
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
