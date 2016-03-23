package com.inin.Bank.Server;

import com.inin.Bank.Domain.Account;
import com.inin.Bank.Domain.Customer;
import com.inin.Bank.Domain.KycInfo;
import com.inin.Bank.Domain.Transaction;
import com.inin.Bank.Exceptions.BadDataException;
import com.inin.Bank.Util.Util;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by evansbelly on 23/3/16.
 */
public class BankService {
    /**
     * Generates a valid customer object.
     * @param scanner
     * @return valid Customer object.
     */
    public static Customer getCustomerInfo(Scanner scanner) {
        Customer customer = null;
        scanner.nextLine();
        System.out.println("Enter the customer details: \nName: ");
        String userName = scanner.nextLine();
        System.out.println("\nAddress: ");
        String address = scanner.nextLine();
        System.out.println("\nContact No.: ");
        long contact = scanner.nextLong();
        System.out.println("\nDoes the customer have KYC docs? Y/N");
        scanner.nextLine();
        String kycAvailable = scanner.nextLine().toUpperCase();
        if (kycAvailable.equals("Y")) {
            System.out.println("\nKYC document name: ");
            String kycDoc = scanner.nextLine();
            System.out.println("\nKYC document path: ");
            String kycDocPath = scanner.nextLine();
            KycInfo kycInfo = KycInfo.createKycInfo(kycDoc, kycDocPath);
            try {
                customer = Customer.createUser(userName, address, contact, kycInfo);
            } catch (NullPointerException n) {
                System.out.println("Blank customer data");
            }
        } else if (kycAvailable.equals("N")) {
            try {
                customer = Customer.createUser(userName, address, contact);
            } catch (NullPointerException n) {
                System.out.println("Blank customer data");
            }
        } else {
            System.out.println("Invalid answer. Please use Y/N");
        }
        return customer;
    }

    /**
     *
     * @param scanner
     * @param customer
     * @param flag
     * @param accountList
     * @return boolean value. True : Account successfully created
     */
    public boolean accountCreateService(Scanner scanner, Customer customer, boolean flag, HashMap<Long, Account> accountList) {
        System.out.println("\nEnter the account details: \nNon zero Account Number: ");
        long accountNo = scanner.nextLong();
        System.out.println("\nAccount balance amount: ");
        double balance = scanner.nextDouble();
        try {
            Account account = Account.createAccount(accountNo, balance, customer);
            Util.serializeAccount(account, accountNo);
            accountList.put(accountNo, account);
            flag = false;
        } catch (IllegalArgumentException i) {
            System.out.println("Invalid account no.");
        } catch (BadDataException e) {
            System.out.println("Balance amount entered is below Rs. 2000/-");
        }
        return flag;
    }

    public void optionSelection(Scanner scanner, int selection, HashMap<Long, Account> accountsList) {
        switch (selection) {
            case 1:
                boolean flag;
                Customer customer = null;
                do {
                    customer = BankService.getCustomerInfo(scanner);
                } while (customer == null);

                do {
                    flag = this.accountCreateService(scanner, customer, true, accountsList);
                } while (flag);
                break;
            case 2:
                System.out.println("Enter your account No.");
                long accountNo = scanner.nextInt();
                System.out.println("Select option : \n" +
                        "1. Withdrawal \n" +
                        "2. Deposit");
                int ops = scanner.nextInt();
                System.out.println("Enter the amount");
                long amount = scanner.nextLong();
                Account indAccount = accountsList.get(accountNo);
                if (indAccount != null) {
                    switch (ops) {
                        case 1:
                            System.out.println("Balance before Withdrawal : " + indAccount.getBalance());
                            double bal = indAccount.performTransaction(1, amount);
                            System.out.println("Balance after Withdrawal : " + bal);
                            Util.serializeAccount(indAccount, accountNo);
                            break;
                        case 2:
                            System.out.println("Balance before Deposit : " + indAccount.getBalance());
                            bal = indAccount.performTransaction(2, amount);
                            System.out.println("Balance after Deposit : " + bal);
                            Util.serializeAccount(indAccount, accountNo);
                            break;
                    }
                }
                break;
            case 3:
                System.out.println("Enter your account no: ");
                accountNo = scanner.nextInt();
                indAccount = accountsList.get(accountNo);
                System.out.println(indAccount);
                break;
            case 4:
                System.out.println("Enter your account No.");
                accountNo = scanner.nextInt();
                indAccount = accountsList.get(accountNo);
                ArrayList<Transaction> transactions = indAccount.getTransactions();
                System.out.println("Transaction History \n");
                for (Transaction trans : transactions) {
                    System.out.println(trans);
                }
                break;
        }
    }

}
