package com.ininbank.domain;

import com.ininbank.exceptions.LessBalanceException;
import com.ininbank.exceptions.SystemExceptions;
import com.ininbank.solids.InitSystem;
import com.ininbank.solids.SystemConstants;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Deepak on 23/3/16.
 * This represents the Customer Account in system
 */
public class CustomerAccount extends Account implements Serializable{

    private static final long serialVersionUID = 1234567890;
    private final float INTEREST = SystemConstants.SAVING_INTEREST_RATE;

    private float balance;
    private Customer customer;
    private Document[] documents;

    static int count;

    /**
     * Initialize the count number so to create account number*/
    static {
        try {
            count = InitSystem.initAccountNumber();
            System.out.println("COUNT : "+count);

        } catch (SystemExceptions systemExceptions) {
            System.out.println(systemExceptions.getMessage());
        }
    }


    /**
     * Initialize the Customer account with minimum required parametera.
     * @param balance is balance in customer account.
     * @param customer is customer for whome account is created.*/
    public CustomerAccount(float balance, Customer customer){
        this(balance, customer, null);
    }

    /**
     * Initialize the Customer account with all parameters.
     * @param balance is balance in customer account.
     * @param customer is customer for whome account is created.
     * @param documents is documents supply by the customer.*/
    public CustomerAccount(float balance, Customer customer, Document[] documents) {
        //super(accountNumber);
        super(count++);
        this.balance = balance;
        this.customer = customer;
        this.documents = documents;
    }

    public CustomerAccount(int accountNumber, float balance, Customer customer, Document[] documents) {
        super(accountNumber);
        this.balance = balance;
        this.customer = customer;
        this.documents = documents;
    }

    /** This method used for creating new account with minimum parameters*/
/*    public static CustomerAccount createAccount(float balance, Customer customer) {
        int accountNumber = count;
        count++;
        return new CustomerAccount(accountNumber, balance, customer);
    }*/

    /** This method used for creating new account with all parameters*/
/*    public static CustomerAccount createAccount(float balance, Customer customer, Document[] documents) {
        int accountNumber = count;
        count++;
        return new CustomerAccount(accountNumber, balance, customer, documents);
    }*/

    public float getBalance() {
        return balance;
    }

    public void deposit(float amount)
    {
        this.balance += amount;
    }

    public void withdraw(float amount){
        this.balance -= amount;
    }

    @Override
    public String toString() {
        return "CustomerAccount{" +
                "INTEREST=" + INTEREST +
                ", balance=" + balance +
                ", customer=" + customer +
                ", documents=" + Arrays.toString(documents) +
                "} " + super.toString();
    }
}
