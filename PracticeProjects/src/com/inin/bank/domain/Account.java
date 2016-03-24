package com.inin.bank.domain;

import com.inin.bank.exception.InsufficientBalanceException;
import com.inin.bank.exception.LowInitialBalanceException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by root on 23/3/16.
 */
public class Account implements Serializable {
    private static final long serialVersionUID = 1;

    /**
     * Account number
     */
    private String accountNo;

    /**
     * Total amount till date i.e, balance in the account
     */
    private double balance;

    /**
     * The date & time when the account was created
     */
    private LocalDateTime dateCreated;
    /**
     * Customer holding this account
     */
    private Customer customer;
    /**
     * for transaction logs
     */
    private List<Transaction> transactionList = new ArrayList<>();

    /**
     * annual interest rate
     */
    private static final double interestRate = 10;

    /**
     * minimum balance required during account creation
     */
    public final double minBalanceAccountCreation = 2000;
    /**
     * minimum balance required to check while withdrawing
     */
    public final double minBalanceToBeMaintained = 500;


    /**
     * Amount to verify whether pan card number is required during deposit
     */
    public final double panCardBalance = 50000;

    private Lock lock = new ReentrantLock();


    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getMinBalanceAccountCreation() {
        return minBalanceAccountCreation;
    }

    public double getMinBalanceToBeMaintained() {
        return minBalanceToBeMaintained;
    }

    public double getPanCardBalance() {
        return panCardBalance;
    }

    /**
     * @param accountNo account number
     * @param balance   initial balance while account creation
     * @param customer  Customer details
     * @throws LowInitialBalanceException when minBalanceAccountCreation is not satisfied
     */
    private Account(String accountNo, double balance, Customer customer) throws LowInitialBalanceException {
        if (balance < this.minBalanceAccountCreation) {
            throw new LowInitialBalanceException("Insufficient balance");
        }
        this.accountNo = accountNo;
        this.balance = balance;
        this.customer = customer;
        this.dateCreated = LocalDateTime.now();
    }

    /**
     * @param accountNo      account number
     * @param initialBalance initial balance while account creation
     * @param customer       Customer details
     * @return Account object
     * @throws LowInitialBalanceException when minBalanceAccountCreation is not satisfied
     */
    public static Account createAccount(String accountNo, double initialBalance, Customer customer) throws LowInitialBalanceException {
        Account account = new Account(accountNo, initialBalance, customer);
        FileIOUtil.serialize(account, account.accountNo);
        return account;
    }

    /**
     * get account number
     *
     * @return String
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * To print details for account object
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Account{" +
                "accountNo='" + accountNo + '\'' +
                ", balance=" + balance +
                ", dateCreated=" + dateCreated +
                ", customer=" + customer +
                '}';
    }

    /**
     * check whether its a valid withdrawal, as is there sufficient balance & minimum balance criteria satisfied
     *
     * @param amount amount to withdraw
     * @return boolean , whether he can withdraw money
     */
    private boolean canWithdraw(double amount) {
        return (this.balance - this.minBalanceToBeMaintained) > amount;
    }

    /**
     * withdraw money from account
     *
     * @param amount amount to withdraw
     * @return double, the balance left in the account after withdrawing(if its a valid withdraw)
     */
    public double withdraw(double amount) {
        lock.lock();
        try {
            if (!this.canWithdraw(amount)) {
                throw new InsufficientBalanceException("Minimum balance needs to be 500");
            }
            this.balance -= amount;
            Transaction transaction = Transaction.createTransaction(TransactionType.WITHDRAW, amount);
            transactionList.add(transaction);
            AccountRepository.put(this);
            FileIOUtil.serialize(this, this.accountNo);
        } finally {
            lock.unlock();
        }
        return this.balance;
    }

    /**
     * deposit money into account
     *
     * @param transactionType one of the TransactionType
     * @param amount          amount to deposit
     * @return double, the balance after deposit successful
     * @throws IllegalArgumentException when pan card is required & not provided valid pan number
     */
    public double deposit(TransactionType transactionType, double amount) throws IllegalArgumentException {
        return deposit(transactionType, amount, 0, null);
    }

    /**
     * deposit money into account
     *
     * @param transactionType one of the TransactionType
     * @param amount          amount to deposit
     * @param chequeNo        cheque number
     * @return double, the balance after deposit successful
     * @throws IllegalArgumentException
     */
    public double deposit(TransactionType transactionType, double amount, int chequeNo) throws IllegalArgumentException {
        return deposit(transactionType, amount, chequeNo, null);
    }

    /**
     * deposit money into account
     *
     * @param transactionType one of the TransactionType
     * @param amount          amount to deposit
     * @param panNumber       pan card number
     * @return double, the balance after deposit successful
     * @throws IllegalArgumentException
     */
    public double deposit(TransactionType transactionType, double amount, String panNumber) throws IllegalArgumentException {
        return deposit(transactionType, amount, 0, panNumber);
    }

    /**
     * @param amount    amount to deposit
     * @param panNumber pan card number
     * @return double, the balance after deposit successful
     */
    public double deposit(TransactionType transactionType, double amount, int chequeNo, String panNumber) throws IllegalArgumentException {
        lock.lock();
        try {
            if (isPanCardRequired(amount) && Util.isValidString(panNumber)) {
                throw new IllegalArgumentException("Pan card is required for amount greater than " + panCardBalance);
            }

            this.balance += amount;

            Transaction transaction = Transaction.createTransaction(transactionType, amount, chequeNo, panNumber);
            transactionList.add(transaction);
            AccountRepository.put(this);
            FileIOUtil.serialize(this, this.accountNo);
        } finally {
            lock.unlock();
        }
        return this.balance;
    }

    /**
     * check whether the pan card is required
     *
     * @param amount amount to check
     * @return boolean
     */
    public boolean isPanCardRequired(double amount) {
        return amount > panCardBalance;
    }

    /**
     * display transaction details for an account
     */
    public void displayTransactionDetails() {
        if (!Util.isValidList(transactionList)) {
            System.out.println("No transaction found!!!");
        } else {
            for (Transaction transaction : transactionList) {
                System.out.println(transaction);
            }
        }
    }
}