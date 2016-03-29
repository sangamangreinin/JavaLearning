package com.inin.domain;

import com.inin.constants.DepositMode;
import com.inin.constants.KYCConstants;
import com.inin.constants.TransactionType;
import com.inin.constants.WithdrawalMode;
import com.inin.utils.Util;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by virendradubey on 23/3/16.
 * Account class hold the account details of the customer
 */
public class Account
        implements Serializable, AccountBase {


    private static final long serialVersionUID = 1;

    /**
     * account number
     *
     */
    private String accountNumber;
    /**
     * balance of the account
     */
    private double balance;
    /**
     * list of transactions made on account
     */
    private List<TransactionHistory> transactions;
    /**
     * datetime on which account is created
     */
    private LocalDateTime dateCreated;

    /**
     * account holders details
     */
    private Person customer;

    private List<KYCDocs> kycs;

    private Account(String name, String address, double balance){
        customer = Customer.createCustomer(name,address);
        this.balance = balance;

    }

    public static Account createAccount(String name, String address, double balance){
        Integer id = Util.readAccountId();
        Account account = new Account(name, address, balance);
        account.setAccountNumber(String.format("SB%05d",id));
        account.setDateCreated(LocalDateTime.now());

        Util.writeAccountId(String.valueOf(id+1));

        return account;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", transactions=" + transactions +
                ", dateCreated=" + dateCreated +
                ", customer=" + customer +
                '}';
    }

    /**
     * get created date
     * @return
     */
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }


    private void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<TransactionHistory> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionHistory> transactions) {
        this.transactions = transactions;
    }

    /**
     * calculates the interest for the account
     * @return interest amount calculated
     */
    @Override
    public double calculateInterest() {
        return 0;
    }

    /**
     * adds the transaction in the List for the account
     * @param dr type of transaction
     * @param value amount of transaction
     */
    @Override
    public void addTransaction(TransactionType dr, double value, DepositMode mode, double total) {
        transactions.add(new TransactionHistory.TransactionHistoryBuilder().forType(dr).withMode(mode).withAmount(value).hasTotal(total).create());
    }

    @Override
    public void addTransaction(TransactionType dr, double value, WithdrawalMode mode, double total) {
        transactions.add(new TransactionHistory.TransactionHistoryBuilder().forType(dr).withMode(mode).withAmount(value).hasTotal(total).create());
    }

    /**
     * creates the initial list for adding transactions
     */
    public void createTransactionsList() {
        this.transactions = new ArrayList<>();
    }

    /**
     * withdraws the amount from the account
     * @param amount amount to be withdrawn
     * @return latest balance of the account
     */
    public double withdrawAmount(double amount) {
        this.balance = this.balance - amount;
        return this.balance;

    }

    /**
     * deposits the amount in the account
     * @param amount amount to be deposited
     * @return latest balance of the account
     */
    public double depositAmount(double amount) {
        this.balance = this.balance+amount;
        return this.balance;
    }

    public List<KYCDocs> getKYCs(){
        return this.kycs;
    }

    /**
     * adds the KYC in account for customer
     * @param pan type of KYC
     * @param panId id of KYC
     */

    public void addInKYC(KYCConstants pan, String panId) {

        if (this.kycs == null){
            this.kycs = new ArrayList<>();
        }
        this.kycs.add(new KYCDocs.KYCDocsBuilder().forType(pan).withUID(panId).create());
    }

    public Person getCustomer(){
        return this.customer;
    }

}
