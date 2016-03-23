package com.inin.Bank.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by root on 23/3/16.
 */
public class Account implements Serializable{
    private static final long serialVersionUID = 10;
    /**
     * account number of the customer
     */
    long accountNumber;
    /**
     * balance of the customer in the account
     */
    float balance;
    /**
     * mode of deposit of amount  can be either cheque or cash
     */
    ModeOfDeposit modeOfDeposit;
    /**
     * customer information to whom the account belongs to
     */
    Customer customer;
    /**
     * Annual interest rate that stores the current interest rate
     */
    static float interestRate = 8.7f;
    /**
     * date and time of account creation
     */
    LocalDateTime createdDate;
    /**
     * stores history of transaction
     */
    List<Transaction> transactions;

    /**
     * initialize the account object
     * @param accountNumber
     * @param balance
     * @param customer
     */

    private Account(long accountNumber, float balance,  Customer customer) {
        this.balance = balance;
        this.customer = customer;
        this.accountNumber = accountNumber;
        this.setCreatedDate();
    }

    /**
     * creates an object of class Account
     * @param accountNumber
     * @param balance
     * @param customer
     * @return a new account with all the necessary properties of the object initialized
     */
    public static Account createAccount(long accountNumber, float balance,  Customer customer){
        if(balance < 2000){
            throw new IllegalArgumentException("While creating an account minimum balance should be 2000");
        }
        Account account = new Account(accountNumber, balance, customer );
        return account;
    }

    /**
     * set created date of an account
     */
    private void setCreatedDate() {
        this.createdDate = LocalDateTime.now();
    }

    /**
     * allows customer to withdraw an amount from account
     * @param amount
     * @return the balance in the accounts
     * @throws IllegalArgumentException if balance found in the account is less than or equal to 500 user cannot withdraw
     */
    public float withdraw(float amount){
        if(balance <= 500){
            throw new IllegalArgumentException("you cannot withdraw since the account should have minimum 500 balance");
        }
        this.balance = this.balance - amount;
        this.transactionHistory(amount, "WITHDRAW");
        return this.balance;
    }

    /**
     * maintains transaction history of customer
     * @param amount
     */
    private void transactionHistory(float amount, String transactionType){
        Transaction transaction = Transaction.createTransaction(this.accountNumber, amount, transactionType);
        this.transactions.add(transaction);
    }

    /**
     * allows customer to deposit an amount to account
     * @param amount
     * @param modeOfDeposit
     * @return balance in the account of customer
     * @throws if mode of deposit is not cheque or cash
     */
    public float deposit(float amount, ModeOfDeposit modeOfDeposit){
        if(modeOfDeposit == ModeOfDeposit.CASH || modeOfDeposit == ModeOfDeposit.CHEQUE){
            this.modeOfDeposit = modeOfDeposit;
            this.balance = this.balance + amount;
            this.transactionHistory(amount, "DEPOSIT");
            return this.balance;
        }else if(amount > 50000){
            //ask for pan card details
            KycDocument kycDocument = PanCard.createPanCard("DKLYS2345M");
        }
        throw new IllegalArgumentException("Mode of deposit can be either cash or cheque");
    }

    /**
     * user or customer can view all the history of transaction happened in his/her account
     */
    public void displayListOfAllTransaction(){
        for (Transaction transaction : transactions){
            System.out.println(transaction);
        }
    }

    /**
     * get list of all transaction happened in user or customer account
     * @return list of transaction obejct
     */
    public List<Transaction> getListOfAllTransaction(){
        return Collections.unmodifiableList(transactions);
    }

    /**
     * get list of transactions happened on user account between date range
     * @param fromDate
     * @param toDate
     */
    public List<Transaction> getTransactionDetailsBetweenDateRange(String fromDate, String toDate){
        LocalDateTime startDate = LocalDateTime.parse(fromDate);
        LocalDateTime endDate = LocalDateTime.parse(toDate);

        List<Transaction> filteredListTransaction = new ArrayList<>();
        for (Transaction transaction : transactions){
            if(transaction.transactionDate.isAfter(startDate) && transaction.transactionDate.isBefore(endDate)){
                filteredListTransaction.add(transaction);

            }
        }
        return filteredListTransaction;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", modeOfDeposit=" + modeOfDeposit +
                ", customer=" + customer +
                ", createdDate=" + createdDate +
                '}';
    }
}
