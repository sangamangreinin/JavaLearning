package com.inin.service;

import com.inin.exception.TransactionNotAllowedException;
import com.inin.model.*;
import com.inin.util.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by root on 23/3/16.
 */
public class AccountService {

    public Map<String, Account> accounts = new HashMap<>();
    Lock lock = new ReentrantLock();

    public String openNewAccount(String name,String address,String phoneNumber,double balance,Map<String,Map<String ,String>> kycDoc){
        if(!Util.isValidString(name) && !Util.isValidString(address) && !Util.isValidString(phoneNumber))
            throw new InvalidParameterException("Customer name, address and phone number is required");
        if(balance < Account.MIN_OPENING_BALANCE)
            throw new InvalidParameterException("Balance should be greater than 2000");

        Set<KYCDocument> kycDocuments = new HashSet<>();
        kycDoc.forEach((key, value) -> {
            if(key.equals("pan"))
                kycDocuments.add(new KYCDocument(value.get("panCardId"),"pan",value.get("panCardPath")));
            if(key.equals("adhar"))
                kycDocuments.add(new KYCDocument(value.get("adharCardId"),"adhar",value.get("adharCardPath")));
            if(key.equals("passport"))
                kycDocuments.add(new KYCDocument(value.get("passportId"),"passport",value.get("passportPath")));
        });

        Customer customer = new Customer(name,address,phoneNumber,kycDocuments);
        Transaction transaction = null;
        if(balance < 50000)
             transaction = new Transaction("deposit",balance,null);
        else{
            if(kycDoc.get("pan") == null)
                throw new InvalidParameterException("Pan card required if deposit balance is greater than 50000");
            else
                transaction = new Transaction("deposit",balance,kycDoc.get("pan").get("panCardId"));
        }
        Account account = new CurrentAccount(balance,customer,transaction);
        Util.serialize(account);
        accounts.put(account.getAccId(),account);
        return account.getAccId();
    }

    /**
     * Withdraw provided amount from provided account Id
     * @param accId Customer Account Id
     * @param withdrawAmount Withdraw Amount
     * @return boolean
     */
    public void withdraw(String accId, double withdrawAmount){
        if(!accounts.containsKey(accId))
            throw new InvalidParameterException("Invalid Account Id");
        try {
            lock.lock();
            Account account = accounts.get(accId);
            if(account.getBalance()-500 < withdrawAmount)
                throw new TransactionNotAllowedException("Transaction not allowed because you not have enough balance");
            account.withdraw(withdrawAmount);
            account.addTransaction(new Transaction("withdraw",withdrawAmount,null));
            Util.serialize(account);
        }finally {
            lock.unlock();
        }
    }

    /**
     * Deposit provided amount into provided account Id
     * @param accId Customer Account Id
     * @param depositAmount Withdraw Amount
     * @return boolean
     */
    public void deposit(String accId, double depositAmount,String panCardNumber){
        if(depositAmount > 50000 && !Util.isValidString(panCardNumber))
            throw new InvalidParameterException("Pan Card Number must be required when deposit amount greater than 50000");
        if(!accounts.containsKey(accId))
            throw new InvalidParameterException("Invalid Account Id");
        try {
            lock.lock();
            Account account = accounts.get(accId);
            account.deposit(depositAmount);
            account.addTransaction(new Transaction("deposit",depositAmount,panCardNumber));
            Util.serialize(account);
        }finally {
            lock.unlock();
        }

    }

    /**
     * Return Account Details
     * @param accId
     * @return Map<String, String>
     */
    public Map<String, String> getAccountDetail(String accId){
        if(!accounts.containsKey(accId))
            throw new InvalidParameterException("Invalid Account Id");
        Map<String,String> accountDetails  = new HashMap<>();
        Account account = accounts.get(accId);
        accountDetails.put("accId",account.getAccId());
        accountDetails.put("accountBalance",Double.toString(account.getBalance()));
        return accountDetails;
    }

    /**
     * Return all transaction History of provided account id
     * @param accId
     * @return List<Transaction>
     */
    public List<Transaction> getAllTransaction(String accId){
        if(!accounts.containsKey(accId))
            throw new InvalidParameterException("Invalid Account Id");
        Account account = accounts.get(accId);
        return Collections.unmodifiableList(account.getAllTransaction());
    }

    /**
     * Return all transaction History of provided account id and  date range
     * @param accId
     * @return List<Transaction>
     */
    public List<Transaction> getAllTransaction(String accId, LocalDateTime fromDate, LocalDateTime toDate){
        if(!accounts.containsKey(accId))
            throw new InvalidParameterException("Invalid Account Id");
        if(fromDate.compareTo(toDate) > 0)
            throw new InvalidParameterException("Invalid Date Range");
        Account account = accounts.get(accId);
        List<Transaction> filteredTransaction = new ArrayList<>();
        List<Transaction> transactions = account.getAllTransaction();
        for(Transaction transaction : transactions){
            if(transaction.getTransactionDate().compareTo(fromDate) >= 0 && transaction.getTransactionDate().compareTo(toDate) <= 0)
                filteredTransaction.add(transaction);
        }
        return Collections.unmodifiableList(filteredTransaction);
    }

    /**
     * Load Stored Account details
     */
    public void loadStoredAccountDetails(){
        File file = new File("src/main/resources/account");
        File[] files = file.listFiles();
        for (File file1 : files){
            try {
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file1));
                Account account = (Account)inputStream.readObject();
                accounts.put(account.getAccId(),account);
            }catch (IOException ioe){
                ioe.printStackTrace();
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }
}
