package com.inin.bank.domain;

import com.inin.bank.domain.Exception.DataNotFound;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by root on 23/3/16.
 */
public class BankService {

    // contains CustomerAccount against the acc no
    static HashMap<Integer, CustomerAccount> accHashMap = new HashMap<>();

    /*
     */
    public BankService() {
        FileOperation.getLoad();
    }


    private Lock lock = new ReentrantLock();
        

    /**
     *
     * @param accNo
     * @param customerName customer name
     * @param address address
     * @param contactNo contact number
     * @return  CustomerAccount Object
     * @throws IllegalArgumentException
     */
    public static CustomerAccount create(int accNo, String customerName, String address, long contactNo) throws IllegalArgumentException {
        if (accHashMap.containsKey(accNo)) {
            throw new IllegalArgumentException("Acc no already exist");
        }
        return new CustomerAccount(accNo, new CustomerInfo(customerName, address, contactNo));
    }

    /**
     * \
     * this method for create new account
     *
     * @param accNo account number
     * @param customerName  customer name
     * @param address
     * @param contactNo
     * @param kycDoc
     * @return
     */
    public static CustomerAccount create(int accNo, String customerName, String address, long contactNo, HashMap<String, String> kycDoc) {

        if (accHashMap.containsKey(accNo)) {
            throw new IllegalArgumentException("Acc no already exist");
        }

        CustomerAccount customerAccount = new CustomerAccount(accNo, new CustomerInfo(customerName, address, contactNo, kycDoc));

        if (customerAccount != null) {
            if (customerAccount.getAccNo() == accNo) {
                System.out.println(customerAccount);
                FileOperation.writeObj(customerAccount);
                FileOperation.getLoad();
                System.out.println("Account has been Created Successfully");
            }
        }
        return customerAccount;

    }

    /**
     * @param amount amount to withdraw
     * @param accNo  account
     * @return
     */

    public double withdraw(double amount, int accNo) {

        lock.lock();
        CustomerAccount customerAccount = accHashMap.get(accNo);
        try {
            if (amount > 0 && (customerAccount.getBalance() - amount) > 500) {
                double bal = customerAccount.getBalance();
                bal -= amount;
                customerAccount.setBalance(bal);
                customerAccount.getTransactions().add(new Transaction("withdraw", amount));
                FileOperation.writeObj(customerAccount);
                FileOperation.getLoad();
                return customerAccount.getBalance();
            } else {
                throw new IllegalArgumentException(" Cant WithDraw Money ::->  amount should be greater then zero and min Balance 500 in account");
            }

        } finally {
            lock.unlock();
        }
    }

    /**
     * this method for deposit amt in account
     *
     * @param amount      amount to be Deposit in account
     * @param depositType cash or cheque
     * @param pan         pan card if amt is greater the limit
     * @return
     * @throws IllegalArgumentException
     */

    public double deposit(double amount, String depositType, String pan, int accNo) throws IllegalArgumentException {
        if (amount < 1) {
            throw new IllegalArgumentException(" Amount Should be Greater then zero");
        }

        lock.lock();
        CustomerAccount customerAccount = accHashMap.get(accNo);
        try {
            double bal = customerAccount.getBalance();
            bal += amount;
            customerAccount.setBalance(bal);
            if (amount > CustomerAccount.TRANSACTION_LIMIT_FOR_PAN) {
                customerAccount.getTransactions().add(new Transaction("deposit", amount, "cash", pan));
            } else {
                customerAccount.getTransactions().add(new Transaction("deposit", amount, "cash"));
            }
            FileOperation.writeObj(customerAccount);
            FileOperation.getLoad();
            return customerAccount.getBalance();
        } finally {
            lock.unlock();
        }
    }

    /**
     * view account details
     *
     * @param accNo
     * @return
     */
    public CustomerAccount viewAccDetails(int accNo) {
        return accHashMap.get(accNo);
    }


    /**
     * @param startDateTime startDate for Transaction History
     * @param endDateTime   endDate for Transaction History
     * @return
     * @throws DataNotFound thorw the exception when data is empty in data base
     */
    public ArrayList<CustomerAccount> viewTransactionHistory(LocalDateTime startDateTime, LocalDateTime endDateTime) throws DataNotFound {

        ArrayList<CustomerAccount> list = new ArrayList<>();

        for (CustomerAccount customerAccount : accHashMap.values()) {
            if (customerAccount.getDateCrated().isBefore(endDateTime) && customerAccount.getDateCrated().isAfter(startDateTime)) {
                list.add(customerAccount);
            }
        }
        if (list.isEmpty()) {
            throw new DataNotFound("Data not Found");
        }

        return list;
    }
}
