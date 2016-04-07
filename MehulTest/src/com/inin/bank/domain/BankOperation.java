package com.inin.bank.domain;

import com.inin.bank.domain.Exception.DataNotFound;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by root on 23/3/16.
 */
public class BankOperation {

    Scanner scanner = new Scanner(System.in);

    static BankOperation menuClass;
    static BankService bankService = new BankService();

    public BankOperation() {
    }

    private void displayMenu() {


        System.out.println("----------- Menu-----------");
        System.out.println("i) Open a new account");
        System.out.println("ii) Perform withdrawl/deposit on an account");
        System.out.println("iii) View the details of my accountt");
        System.out.println("iv) View the transaction history of my account");
        System.out.println("v) Add my KYC documents");

        int menu = menuClass.getInput();

        switch (menu) {
            case 1:
                menuClass.createAccount();
            case 2:
                menuClass.withdrawDeposit();
            case 3:
                menuClass.viewAccDetails();
            case 4:
                menuClass.viewTransactionHistory();
        }
    }

    /**
     *
     */
    private void viewTransactionHistory() {
        System.out.println("enter the account number");
        int accNo = scanner.nextInt();
        scanner.nextLine();

        System.out.println("enter the Start date format yyyy-MM-dd HH:mm");
        String startDate = scanner.nextLine();//1970-01-01 12:12
        System.out.println("enter the Start date format yyyy-MM-dd HH:mm");
        String endDate = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDateTime = LocalDateTime.parse(startDate, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate, formatter);
        System.out.println(endDate);

        CustomerAccount customerAccount = FileOperation.readObj(accNo);
        ArrayList<CustomerAccount> arrListAccount = bankService.viewTransactionHistory(startDateTime, endDateTime);
        if (!arrListAccount.isEmpty()) {
            for (CustomerAccount custAcc : arrListAccount) {
                System.out.println(custAcc);
            }
        }
        menuClass.displayMenu();
    }

    /**
     *
     */
    private void viewAccDetails() {
        System.out.println("enter the account number");
        int accNo = scanner.nextInt();
        System.out.println(bankService.viewAccDetails(accNo));
        menuClass.displayMenu();
    }

    /**
     *
     */
    private void withdrawDeposit() {
        System.out.println("enter the account number");
        int accNo = scanner.nextInt();

        System.out.println("----------- SubMenu-----------");
        System.out.println("i) Deposit");
        System.out.println("ii) withdraw");

        int subMenu = scanner.nextInt();
        if (subMenu == 1) {
            System.out.println("enter the amt to deposit");
            double amt = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("enter the deposit type cheque or cash");
            String typeDeposit = scanner.nextLine();

            String pan = null;
            if (amt > CustomerAccount.TRANSACTION_LIMIT_FOR_PAN) {
                System.out.println("enter the PAN Number");
                pan = scanner.nextLine();
            }

            System.out.println("after deposit your Balance is" + bankService.deposit(amt, typeDeposit, pan, accNo));
        } else if (subMenu == 2) {

            System.out.println("enter the amt to withdraw");
            double amt = scanner.nextDouble();

            System.out.println("after withdraw your Balance is  " + bankService.withdraw(amt, accNo));
        }

        menuClass.displayMenu();

    }

    /**
     * Start Program
     * @param args
     */
    public static void main(String[] args) {
        menuClass = new BankOperation();
        menuClass.displayMenu();
    }

    /**
     * create Account
     */
    private void createAccount() {

        System.out.println("enter the account number");
        int accNo = scanner.nextInt();
        scanner.nextLine();

        System.out.println("enter customer name");
        String name = scanner.nextLine();
        System.out.println("enter customer address");
        String address = scanner.nextLine();
        System.out.println("enter customer mob no");
        long mobNo = scanner.nextLong();
        scanner.nextLine();

        System.out.println("wont to enter KYC Doc Press [Y]");
        String kyc = scanner.next();


        HashMap<String, String> kycDoc = new HashMap();
        if (kyc.equals("Y")) {
            System.out.println(" enter KYC type ");
            String kycType = scanner.nextLine();
            scanner.nextLine();

            System.out.println(" enter KYC Path ");
            String kycPath = scanner.nextLine();
            kycDoc.put(kycType, kycPath);
        }


        bankService.create(accNo, name, address, mobNo, kycDoc);
        menuClass.displayMenu();
    }


    private int getInput() {
        int menu = scanner.nextInt();
        return menu;
    }
}

