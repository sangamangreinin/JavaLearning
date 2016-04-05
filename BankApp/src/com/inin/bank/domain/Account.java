package com.inin.bank.domain;

import java.io.*;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by lokesh on 23/3/16.
 * Account Modal representing a Account entity
 */
public class Account implements Serializable {

    private static final int serialVersionUID = 1;
    private final static float interestRate = 7.5f;
    private long accountNo;
    private double balance;
    private Customer customer;
    private LocalDateTime dateCreated;
    private List<Transaction> transactions;

    /**
     * Constructor to create Account object
     *
     * @param initialBalance Initial balance in Account
     * @param name           Name of Customer to which this account will belong
     * @param phone          phone of Customer to which this account will belong
     */
    public Account(double initialBalance, String name, String phone) {
        validateAccount(initialBalance);
        this.accountNo = Integer.toUnsignedLong(UUID.randomUUID().hashCode());
        // this.balance = initialBalance;
        this.customer = new Customer(name, phone);
        this.dateCreated = LocalDateTime.now();
        transactions = new ArrayList<>();
        this.deposit(initialBalance,null,0);
        putAccount();
    }

    /**
     * Validates a new account at the time of creation.
     * @param initialBalance initial balance to be deposited in account as opening balance.
     * @throws InvalidParameterException If initial deposit balance is lower than ₹2000/-
     */
    private static void validateAccount(double initialBalance) {
        if (initialBalance < 2000) {
            throw new InvalidParameterException("Minimum amount required to open an account is: ₹2000/-");
        }
    }

    /**
     * Get the account Object based on Account Id passed
     *
     * @param accountNo Account Number for which details needs to be fetched
     * @return Account Object retrieved from File
     * @throws IOException If file related to account can not be opened due to some reason (access denied or file not found.
     * @throws ClassNotFoundException If Object from serialized object cannot be
     */
    public static Account getAccount(long accountNo) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File(Constants.ACCOUNTS_BASE_PATH + accountNo + ".ser")));
        Account account = (Account) objectInputStream.readObject();
        objectInputStream.close();
        return account;
    }

    /**
     * Withdrawal function to withdraw/transfer the specified amount in Cash or By Cheque on Current object
     *
     * @param amount       Amount to be withdrawn
     * @param chequeNumber cheque number is transferring by Cheque
     * @return Available Amount after the withdrawal/transfer
     * @throws InvalidParameterException When account have insufficient balance to withdraw.
     */
    public double withdrawal(double amount, long chequeNumber) {
        synchronized (this) {
            if ((balance - amount) < 500)
                throw new InvalidParameterException("Your balance is Low. You can not withdraw given amount. Try with amount lower than " + (balance - 500));
            balance -= amount;
            Transaction transaction;
            if(chequeNumber==0)
                transaction = new Transaction(TransactionType.CASHWITHDRAWAL, amount, chequeNumber);
            else
                transaction = new Transaction(TransactionType.CHEQUETRANSFER, amount, chequeNumber);
            transactions.add(transaction);
            putAccount();
            return balance;
        }
    }

    /**
     * Deposit the amount in current account
     *
     * @param amount       Amount to deposit
     * @param panNumber    Pan Number (Mandatory for amount>=₹50000
     * @param chequeNumber Cheque Number if deposited by Cheque (Can be null)
     * @return Return available balance after deposit
     * @throws InvalidParameterException When transaction of more than ₹50000/- is performed and PAN Card is not provided.
     */
    public double deposit(double amount, String panNumber, long chequeNumber) {
        if (amount > 50000 && (panNumber.trim().equals("") || panNumber == null))
            throw new InvalidParameterException("Pan Number is required for transaction amount greater than ₹50000/-");
        balance += amount;
        Transaction transaction;
        if(chequeNumber == 0)
            transaction = new Transaction(TransactionType.CASHDEPOSIT, amount, chequeNumber, panNumber);
        else
            transaction = new Transaction(TransactionType.CHEQUEDEPOSIT, amount, chequeNumber, panNumber);
        transactions.add(transaction);
        putAccount();
        return balance;
    }

    /**
     * Serializes the current account
     */
    private void putAccount() {
        ObjectOutputStream objectOutputStream =  null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(Constants.ACCOUNTS_BASE_PATH + accountNo + ".ser")));
            objectOutputStream.writeObject(this);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

/*    public void addKYC() {

    }*/

    /**String representation of Account
     * @return Converts current object to String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer("Account Number:" + accountNo + " Balance:" + balance + " DateCreated:" + dateCreated + customer);
        buffer.append("das");
        if (transactions.size() > 0) {
            transactions.forEach(transaction -> {
                buffer.append(transaction);
//                buffer = null;
            });
        }
        return buffer.toString();
    }


}
