package com.inin.bank.util;

import com.inin.bank.BankOperation;
import com.inin.bank.model.Transaction;
import com.inin.bank.model.BankAccount;

import java.util.List;
import java.util.Scanner;

/**
 * Created by leroy on 24/3/16.
 *
 * This Class is responsible to get the input from the Customer and pass it to Bank operations for further processing
 */
public class ConsoleReader {

    private Scanner scanner;

    private BankOperation bankOperation;

    public ConsoleReader(){
        scanner = new Scanner(System.in);
        bankOperation = new BankOperation();
    }

    public void createAccount(){
        String kyc = null,path = null;
        System.out.println("\nEnter the your name.");
        String name = scanner.next();

        System.out.println("\nEnter the your address.");
        String address = scanner.next();

        System.out.println("\nEnter the your contact number.");
        int contact = scanner.nextInt();

        System.out.println("\nEnter the amount to deposit and should be minimum Rs 2000");
        float balance = scanner.nextFloat();

        System.out.println("\nDo you wish to enter any KYC Documents ? Y / N ");
        String isKyc = scanner.next();

        if (isKyc.toLowerCase().equals("y")){
            System.out.println("Enter Document you wish to enter ? PAN card / Aadhar / Passport");
            kyc = scanner.next();

            System.out.println("Enter Document path");
            path = scanner.next();
        }

        int accountNo = Util.generateRandom();

        bankOperation.create(accountNo, balance, name, address, contact, kyc, path);

        System.out.println("Account Created..!!" +
                "\nPlease note your account No : "+accountNo);
    }

    public void withdrawDeposit(){
        System.out.println("Enter an Account number.");
        int accNo = scanner.nextInt();
        if (bankOperation.checkAccountExists(accNo)){
            BankAccount b = bankOperation.get(accNo);
            System.out.println("Select an appropriate option\n" +
                    "1 - Withdraw\n" +
                    "2 - Deposit");

            int transactType = scanner.nextInt();
            double amount = 0.0;
            String panNo = "";

            if (transactType == 1){
                System.out.println("Enter an Amount.");
                amount = scanner.nextDouble();
                bankOperation.withdraw(b, amount);
            }
            else if (transactType == 2){
                System.out.println("Enter an Amount.");
                amount = scanner.nextDouble();

                System.out.println("Cheque / Cash.");
                String mode = scanner.next();

                if (amount >= 50000){
                    System.out.println("Enter your Pan No.");
                    panNo = scanner.next();
                }

                bankOperation.deposit(b, amount,mode,panNo);
            }
            else {
                System.out.println("Wrong option. Please choose 1 or 2.");
            }

            System.out.println("Balance is :"+b.getBalance());

        }
        else{
            System.out.println("Account doesn't exist.");
        }
    }

    public void viewAccountDetails(){
        System.out.println("Enter an Account number to View details.");
        int cust = scanner.nextInt();

        if (bankOperation.checkAccountExists(cust)){
            System.out.print(bankOperation.printAccountDetails(cust));
        }
        else{
            System.out.println("Account doesn't exist.");
        }
    }

    public void viewHistory(){
        System.out.println("Enter an Account number to View Transaction details.");
        int acc = scanner.nextInt();

        if (bankOperation.checkAccountExists(acc)){
            System.out.println("Press \n1 - All \n" +
                    "2 - specific date range");
            int option = scanner.nextInt();

            if (option == 1){
                bankOperation.printTransactionDetails(acc);
            }

            if (option == 2){
                System.out.println("Enter the From date in this format dd-mm-yyyy.");
                String from = scanner.next();

                System.out.println("Enter the To date in this format dd-mm-yyyy.");
                String to = scanner.next();


                List<Transaction> transactions = bankOperation.printTransactionFromRange(acc, from, to);

                if (!transactions.isEmpty()){
                    for (Transaction transaction : transactions){
                        System.out.println(transaction.toString());
                    }
                }
                System.out.println("Total records found :"+transactions.size()+"\n");
            }

        }
        else {
            System.out.println("Account doesn't exist.");
        }
        System.out.println("Nothing to display");
    }
}
