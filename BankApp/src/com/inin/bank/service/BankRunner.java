package com.inin.bank.service;

import com.inin.bank.domain.Account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lokesh on 23/3/16.
 */
public class BankRunner {
    static Scanner scanner = new Scanner(System.in);
    /*static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();*/
    static String[] actions = {"1. Create Account", "2. Cash Withdraw From Account", "3. Cash Deposit into Account", "4. Cheque Transfer from Account", "5. Cheque Deposit into Account", "6. Display Account Detail"};

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        displayMenu();
        int choice = readInt("Enter your choice:");
        while (choice < 7 && choice > 0) {
            double amount;
            long accountId, chequeNUmber = 0;
            String panNumber = null;
            switch (choice) {
                case 1:
                    amount = readDouble("Enter the initial deposit amount: ");
                    String name = readString("Enter Customer Name: ");
                    String phone = readString("Enter Customer phone number: ");
                    try {
                        Account account = new Account(amount, name, phone);
                        System.out.println("Account Created: " + account);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    accountId = readLong("Enter AccountNo from which to withdraw: ");
                    amount = readDouble("Enter amount to withdraw: ");
                    withDraw(accountId,amount,chequeNUmber);

                    break;
                case 3:
                    accountId = readLong("Enter AccountNo in which to deposit: ");
                    amount = readDouble("Enter amount to deposit: ");
                    if (amount >= 50000.0)
                        panNumber = readString("Enter PAN Card Number: ");
                    deposit(accountId,amount,chequeNUmber,panNumber);
                    break;

                case 4:
                    accountId = readLong("Enter AccountNo from which to transfer: ");
                    amount = readDouble("Enter amount to transfer: ");
                    chequeNUmber = readLong("Enter Cheque Number: ");
                    withDraw(accountId,amount,chequeNUmber);
                    break;
                case 5:
                    accountId = readLong("Enter AccountNo in which to deposit cheque: ");
                    amount = readDouble("Enter amount to deposit: ");
                    chequeNUmber = readLong("Enter cheque number: ");
                    if (amount >= 50000.0)
                        panNumber = readString("Enter PAN Card Number: ");
                    deposit(accountId,amount,chequeNUmber,panNumber);
                    break;
                case 6:
                    try {
                        long accountNo = readLong("Enter Account Number:");
                        Account account = Account.getAccount(accountNo);
                        System.out.println(account);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
            displayMenu();
            choice = readInt("Enter your choice:");
        }
    }

    /**
     * Static function to withdraw some amount from account or transfer by cheques
     * @param accountId Account id of Account from which the amount need to be withdrawn
     * @param amount amount to withdraw
     * @param chequeNUmber cheque number if transferring to other account (can be null)
     */
    public static void withDraw(long accountId,double amount,long chequeNUmber) {
        try {
                        /*lock.lock();*/
            Account account = Account.getAccount(accountId);
            double balance = account.withdrawal(amount, chequeNUmber);
                        /*condition.signalAll();
                        lock.unlock();*/
            System.out.println("Available balance is: " + balance);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Please check the account number.");
        }
    }

    /**
     * Static function to Deposit amount into an account
     * @param accountId Account Id of Account in which amount needs to be deposited
     * @param amount Amount which will be deposited
     * @param chequeNUmber Cheque number of depositing by Cheque (Can be Null for Cash Deposit)
     * @param panNumber Pan Card Number if Depositing >= ₹50000 (Not required otherwise)
     */
    public static void deposit(long accountId, double amount, long chequeNUmber, String panNumber) {
        try {
            Account account = Account.getAccount(accountId);
            double balance = account.deposit(amount, panNumber, chequeNUmber);
            System.out.println("Available balance is: " + balance);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Function to display menus
     */
    public static void displayMenu() {
        for (String action : actions) {
            System.out.println(action);
        }
    }

    /**
     * Reads the input String from user
     * @param message Message to be displayed to use as prompt.
     * @return Returns the string entered by user.
     */
    public static String readString(String message) {
        System.out.println(message);
        String userInput = "";
        try {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            userInput = consoleReader.readLine();
        } catch (IOException ie) {
            System.out.println(ie.getMessage());
        }
        if (userInput.trim().equals("") || userInput == null)
            userInput = readString(message);
        return userInput;
    }

    /**
     * Read the double value from User
     * @param message Message to be displayed to use as prompt.
     * @return Returns the Double casted value of string entered by user.
     */
    public static double readDouble(String message) {
        double input = 0.0;
        try {
            input = Double.parseDouble(readString(message));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            input = readDouble(message);
        }
        return input;
    }

    /**
     * Read the Integer value from User
     * @param message Message to be displayed to use as prompt.
     * @return Returns the Integer casted value of string entered by user.
     */
    public static int readInt(String message) {
        int input = 0;
        try {
            input = Integer.parseInt(readString(message));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            input = readInt(message);
        }
        return input;
    }

    /**
     * Read the Long value from User
     * @param message Message to be displayed to use as prompt.
     * @return Returns the Long casted value of string entered by user.
     */
    public static long readLong(String message) {
        long input = 0;
        try {
            input = Long.parseLong(readString(message));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            input = readLong(message);
        }
        return input;
    }
}
