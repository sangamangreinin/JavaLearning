package com.inin.bank.service;

import com.inin.bank.domain.*;
import com.inin.bank.exception.InSufficientBalanceException;
import com.inin.bank.exception.TransactionNotAllowedException;
import com.inin.bank.serialization.AccountSerialization;
import com.inin.bank.util.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.InvalidParameterException;
import java.util.*;

/**
 * Created by root on 23/3/16.
 *
 */
public class StartBank {

    Map<Long, Account> accountMaster = new HashMap<Long, Account>();
    /**
     * taking user input
     */
    private void startApplication(){
        getAccountDetailsFromMemory();
        int userInput;
        do {
            displayMenuList();
            userInput = Util.readInteger();
            performTicketAction(userInput);
        }while (userInput != 6);
    }

    /**
     * display menu list for bank
     */
    private void displayMenuList(){
        String[] menuArray = new String[]{  "1.Open a new account.",
                                            "2.Withdraw an amount.",
                                            "3.Deposit the amount.",
                                            "4.View transaction history.",
                                            "5.Add KYC documents.",
                                            "What do you want to perform? Please enter your choice :: "};

        for (String menu : menuArray){ System.out.println(menu); }
    }

    /**
     * performing actions on user given input
     * @param input  user choice
     */
    private void performTicketAction(int input){
        switch(input){
            case 1 : openNewAccount();
                break;
            case 2 : withDraw();
                break;
            case 3 : deposit();
                break;
            case 4 : viewTransactions();
                break;
            case 5 : addKyc();
                break;
            case 6 :
                System.out.println("End of operation");
            default:
                System.out.println("Wrong Choice..Please enter valid choice!!!");
        }
    }

    /**
     * creating the new account & customer.
     * deserialize the account object with customer & null transactions
     */
    private void openNewAccount(){
        System.out.println("Enter customer name : ");
        String name = Util.readString();

        System.out.println("Enter customer address : ");
        String address = Util.readString();

        System.out.println("Enter customer contact number : ");
        String contact = Util.readString();

        System.out.println("Enter KYC type (PAN / Aadhar card / Passport): ");
        String kycType = Util.readString(); // need to check valid kyc

        System.out.println("Enter KYC document path : ");
        String kycPath = Util.readString();

        double initialBalance = 0.0;
        do {
            System.out.println("Enter Initial account opening balance (amount should be greater than Rs. 2000) : ");
            initialBalance = Util.readDouble();
            if(initialBalance < 2000)
                System.out.println("Low Balance!!! Balance should be greater than Rs.2000");
        }while (initialBalance<2000);

        if (!Util.isValidString(name) && !(Util.isValidString(address)) && !(Util.isValidString(contact)))
            throw new InvalidParameterException("Customer name, address & contact number are compulsory.");

        System.out.println("initialBalance : " + initialBalance);
        // create a new customer
        Set<Kyc> kycDetails = new HashSet<Kyc>();
        kycDetails.add(new Kyc(kycType, kycPath));
        Customer customer = new Customer(1,name,address,contact,kycDetails);
        Transaction transaction = null;
        if(initialBalance > 50000){
            transaction = new Transaction("deposit", "cash",initialBalance);
        }else {
            if(!kycType.equals("pan")){
                //throw new InvalidParameterException("PAN number is required if depositing balance more than Rs. 50000");
            }
        }

        Account newAccount = new Account(initialBalance,customer, transaction);// create a new account
        Util.writeAccountNumber(newAccount.accountNumber); // write account number into the file for automatic generation of the account number
        accountMaster.put(newAccount.accountNumber,newAccount);
        AccountSerialization.serialize(newAccount);// Serialize the account data
        System.out.println("Your Account is created successfully & your account number is : " + newAccount.getAccountNumber());
    }

    /**
     * withdrawing the entered amount from the given account number
     * deserialize the account object with added transactions & updated balance
     */
    private void withDraw(){
        System.out.println("Enter your account Number : ");
        long accountNumber = Util.readInteger();
        if(accountMaster.containsKey(accountNumber)){
            Account account = accountMaster.get(accountNumber);
            System.out.println("Enter withdrawl amount : ");
            double withdrawlAmount = Util.readDouble();
            if(account.getBalance() > withdrawlAmount) {
                account.withdraw(withdrawlAmount);
                if(account.getBalance() > 500){
                    account.addTransaction(new Transaction("withdraw","cash", account.getBalance())); // create new transaction
                    accountMaster.put(accountNumber,account);
                    AccountSerialization.serialize(account); // Serialize the account data
                }
                else {
                    throw new TransactionNotAllowedException("You can not withdraw " + withdrawlAmount + " min balance should be Rs. 500");
                }
            }
            else
            {
                throw new InSufficientBalanceException("Transaction denied due to insufficient balance");
            }
        }
        else
            throw new InvalidParameterException("Entered account number " + accountNumber + " is not present in the system");
    }

    /**
     * depositing the entered amount into the given account number
     * deserialize the account object with added transactions & updated balance
     */
    private void deposit(){
        System.out.println("Enter your account Number : ");
        long accountNumber = Util.readInteger();

        if(accountMaster.containsKey(accountNumber)){
            Account account = accountMaster.get(accountNumber);
            System.out.println("Enter amount to deposit : ");
            Double depositAmount = Util.readDouble();

            String panNumber = "";
            if(depositAmount > 50000) {
                System.out.println("Enter PAN number");
                panNumber = Util.readString();
            }
            if(depositAmount > 50000 && !(Util.isValidString(panNumber)))
                throw new InvalidParameterException("PAN is compulsory ");

            account.deposit(depositAmount);
            Transaction transaction = new Transaction("deposit","cash", account.getBalance());
            transaction.setPanNumber(panNumber);
            account.addTransaction(transaction);  // add transaction into account object
            AccountSerialization.serialize(account);
            accountMaster.put(accountNumber,account);
        }
        else
            System.out.println("Entered account number " + accountNumber + " is not present in the system");
    }

    /**
     * Displaying all the transactions for entered account number
     */
    private void viewTransactions(){
        System.out.println("Enter your account Number : ");
        long accountNumber = Util.readInteger();

        if(accountMaster.containsKey(accountNumber)){
            Account account = accountMaster.get(accountNumber);
            List<Transaction> transactions = account.getTransactions();
            Iterator<Transaction> iterator = transactions.iterator();
            while (iterator.hasNext()) {
                Transaction transaction = iterator.next();
                System.out.println(transaction);
            }
        }
        else
            throw new InvalidParameterException("Invalid account number");
    }

    private void addKyc(){
        System.out.println("inte : " + Contants.INTEREST);
    }

    public void getAccountDetailsFromMemory(){
        File file = new File("src/main/resources/account");
        File[] files = file.listFiles();
        for (File file1 : files){
            try {
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file1));
                Account account = (Account)inputStream.readObject();
                accountMaster.put(account.getAccountNumber(),account);
            }catch (IOException ioe){
                ioe.printStackTrace();
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        StartBank bankObj = new StartBank();
        bankObj.startApplication();
    }
}
