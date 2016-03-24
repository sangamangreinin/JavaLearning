package com.inin.domain.bank;

import com.inin.domain.bank.helper.FileHelper;
import com.inin.domain.bank.model.BankAccount;
import com.inin.domain.bank.model.Document;
import com.inin.domain.bank.model.Transaction;
import com.inin.domain.bank.model.User;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by leroy on 24/3/16.
 *
 * This Class talks directly with the Bank model classes as the Logic resides here and not exposed to the user directly.
 * Purpose of this Class is to perform all Bank related operations.
 * Also it is Thread safe for critical operations that a customer performs like Withdraw and deposit.
 *
 */
public class BankOperation {

    // Data Store for de-serialized Bank Accounts at the time of Startup.
    private Map<Integer ,BankAccount> bankAccounts;

    // File Helper used to Serialize / de-Serialize
    FileHelper fileHelper;

    // used for Thread Safety below
    Lock lock = new ReentrantLock();

    public BankOperation(){
        /**
         * here we de-serialize all accounts from "vault"
         * directory on initialization of this class and  save it in data store
         */
        fileHelper = new FileHelper();
        bankAccounts = fileHelper.deSerializeAll(new File(FileHelper.DIR));
    }


    /**
     * Creates new Customer Account in the Bank
     * @param accountNo - account number of the Customer
     * @param balance - initial balance sent by the customer
     * @param name - name of the customer
     * @param address - address of the customer
     * @param contact - phone number of the customer
     * @param kyc - kyc document name of the customer
     * @param path - physical path of the kyc document
     *
     *@Note : currently taking only one KYC document from the customer and storing it on Document array
     */
    public void create(int accountNo, double balance, String name, String address, int contact, String kyc, String path){
        Document[] document = new Document[1];
        document[0] = new Document(kyc, path);
        User user = new User(name, address, contact, document);
        BankAccount bankAccount = new BankAccount(accountNo, balance, user);

        if (fileHelper.serialize(accountNo, bankAccount)){;
            this.bankAccounts.put(accountNo, bankAccount);
        }
    }

    /**
     * Withdraws amount from his / her account
     * @param obj - bankAccount containing details of the customer and not the transaction details
     * @param amount - amount that the customer wants to deposit
     * @throws IllegalArgumentException if on withdraw the amount is less than 500
     *
     * @Note - Lock is used in this method to make it thread safe
     */
    public void withdraw(BankAccount obj, double amount){

        lock.lock();
        try{
            double newBalance = obj.getBalance();
            newBalance -= amount;
            if (newBalance < 500)
                throw new IllegalArgumentException("You minimum balance is going below Rs.500. Can't allow this transaction.");

            obj.setBalance(newBalance);
            obj.getTransaction().add(new Transaction(LocalDateTime.now(),"withdraw",null,amount,newBalance, null));
            serializeObj(obj, obj.getAccountNo());
        }
        finally {
            lock.unlock();
        }

    }

    /**
     * deposits amount to his / her account
     * @param obj - bankAccount containing details of the customer and not the transaction details
     * @param amount - amount that the customer wants to deposit
     * @param mode - mode of Payment which is Cheque or Cash
     * @param panNo - if deposit amount is more than 50000 , pan card no is taken from the customer
     *
     * @Note - Lock is used in this method to make it thread safe
     */
    public void deposit(BankAccount obj, double amount, String mode, String panNo){
        lock.lock();
        try {
            String pan = "";
            if (amount >= 50000)
                pan = panNo;

            double newBalance = obj.getBalance();
            newBalance += amount;

            obj.setBalance(newBalance);
            obj.getTransaction().add(new Transaction(LocalDateTime.now(),"deposit",mode, amount, newBalance, pan));
            serializeObj(obj, obj.getAccountNo());
        }
        finally {
            lock.unlock();
        }
    }

    /**
     * prints account details
     * @param accountNo - account number of the Customer
     * @return - string containing the Bank Account details of the Customer without Transaction history
     */
    public String printAccountDetails(int accountNo){
        BankAccount bankAccount = get(accountNo);
        return bankAccount.toString();
    }

    /**
     * prints transaction history of the customer
     * @param accountNo - account number of the Customer
     */
    public void printTransactionDetails(int accountNo){
        BankAccount bankAccount = get(accountNo);
        bankAccount.displayTransactionDetails();
    }

    /**
     * filters transaction according to date specified by customer.
     * @param accountNo - account number of the Customer
     * @param from - from date  in string format dd-MM-yyyy
     * @param to - to date  in string format dd-MM-yyyy
     * @return all transactions in arraylist found according to the date range specified
     */
    public List<Transaction> printTransactionFromRange(int accountNo, String from, String to){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime startDateTime = LocalDateTime.parse(from+" 00:00", formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(to+" 23:59", formatter);

        BankAccount bankAccount = get(accountNo);
        List<Transaction> transactions = new ArrayList<>();

        if (!bankAccount.getTransaction().isEmpty()){
            for ( Transaction transaction: bankAccount.getTransaction()) {
                if (transaction.getTransactionDate().isAfter(startDateTime) && transaction.getTransactionDate().isBefore(endDateTime))
                    transactions.add(transaction);
            }
        }
        return Collections.unmodifiableList(transactions);
    }

    /**
     * Account Checked here
     * @param accountNo  - account number of the Customer
     * @return true if the account exists and false if not present in the data store
     */
    public boolean checkAccountExists(int accountNo){
        return this.bankAccounts.containsKey(accountNo);
    }


    /**
     * Bank account details fetched
     * @param accountNo - account number of the Customer
     * @return - bank account object of the customer
     */
    public BankAccount get(int accountNo){
        return this.bankAccounts.get(accountNo);
    }

    /**
     * serializes customer account to the account file
     * @param account - Bank account object of the customer
     * @param accountNo - - account number of the Customer
     */
    public void serializeObj(BankAccount account, int accountNo){
        fileHelper.serialize(accountNo, account);
    }
}
