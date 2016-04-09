package com.inin.bank.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by leroy on 23/3/16.
 *
 * This Class hold the carious Bank Properties like Account Details and Customer related details
 */
public class BankAccount implements Serializable{

    private static final long serialVersionUID = 456L;

    private int accountNo;  // bank account number

    private double balance; // amount in the account

    transient private static final float rateOfInterest = 12.5f;

    private LocalDateTime dateCreated;  // date and time of account creation

    User user;  // user related information

    private ArrayList<Transaction> transaction; // transaction history on an account

    /**
     *
     * @param accountNo - bank account no
     * @param balance - amount to be deposited by the customer on account creation
     * @param user  - user object containing the user related details like "name , address, phone no, kyc document(optional) "
     * @throws IllegalArgumentException on account creation if the amount deposited by the customer is less than 2000
     */
    public BankAccount(int accountNo, double balance, User user) {

        if (balance < 2000 )
            throw new IllegalArgumentException("Balance cannot be less than Rs.2000.");

        this.accountNo = accountNo;
        this.balance = balance;
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.transaction = new ArrayList<Transaction>();
    }

    public ArrayList<Transaction> getTransaction() {
        return transaction;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void displayTransactionDetails(){
        if (!transaction.isEmpty()){
            for (Transaction history: transaction) {
                System.out.println(history.toString());
            }
            System.out.println("Total records found :"+transaction.size()+"\n");
        }
    }

    @Override
    public String toString() {
        return "-----------------------------------------\n" +
                "BankAccount Details \n" +
                "-----------------------------------------\n" +
                "accountNo : " + accountNo +
                "\n balance : " + balance +
                "\n dateCreated : "  + dateCreated +
                "\n User Details : " + user +"\n" +
                "-----------------------------------------";
    }
}
