package com.inin.bank;

import com.inin.bank.domain.*;
import com.inin.bank.exception.InsufficientBalanceException;
import com.inin.bank.exception.InvalidAccountNumberException;
import com.inin.bank.exception.LowInitialBalanceException;

import java.util.*;

/**
 * Created by root on 23/3/16.
 */
public class AccountManager {
    /**
     * Scanner used for I/O
     */
    Scanner scanner = new Scanner(System.in);

    /**
     * print start menu
     */
    void printStartupMenu() {
        System.out.println("\n\nPlease select from below options");
        System.out.println("1. Open a new account");
        System.out.println("2. Perform withdrawal/deposit on an account");
        System.out.println("3. View transaction history of my account");
        System.out.println("4. Exit/Quit");
    }

    /**
     * starting point of program
     */
    void start() {
        AccountRepository.loadDataOnStartup();
        System.out.println("Welcome to Bank");
        boolean isExit = false;
        do {
            printStartupMenu();
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    processNewAccount();
                    break;
                case 2:
                    processWithdrawalDeposit();
                    break;
                case 3:
                    processViewTransactionHistory();
                    break;
                case 4:
                    System.out.println("Thank for banking with us!!!");
                    isExit = true;
                    break;
                default:
                    System.out.println("Invalid input!!! Please try again");
            }
        }
        while (!isExit);
    }


    /**
     * process new account creation details
     */
    void processNewAccount() {

        System.out.println("Enter Account number");
        String accountNumber = scanner.nextLine();

        System.out.println("Enter initial balance");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter customer name");
        String customerName = scanner.nextLine();

        System.out.println("Enter customer address");
        String customerAddress = scanner.nextLine();

        System.out.println("Enter customer phone");
        String customerPhone = scanner.nextLine();

        System.out.println("Do you want to enter KYC details (Y/N)");
        String option = scanner.nextLine();

        List<KycDocument> kycDocumentList = null;
        if (option.equalsIgnoreCase("Y")) {
            kycDocumentList = getKycDetails();
        }

        Customer customer = null;
        if (Util.isValidList(kycDocumentList)) {
            customer = Customer.createCustomer(customerName, customerAddress, customerPhone, kycDocumentList);
        } else {
            customer = Customer.createCustomer(customerName, customerAddress, customerPhone);
        }

        try {
            Account account = Account.createAccount(accountNumber, initialBalance, customer);

            //add to temporary map
            AccountRepository.put(account);

            System.out.println("Account created successfully!!!");
        } catch (LowInitialBalanceException e) {
            System.out.println("Initial balance should be greater than 2000!!!Please try again\n\n");
        }

    }

    /**
     * get the kyc details from the user
     *
     * @return List of KycDocument
     */
    List<KycDocument> getKycDetails() {
        List<KycDocument> kycDocumentList = new ArrayList<>();

        System.out.println("Enter pan card link");
        String panCard = scanner.nextLine();
        if (!panCard.isEmpty()) {
            KycDocument kycDocument = KycDocument.createKyc(KycDocumentType.PAN_CARD, panCard);
            kycDocumentList.add(kycDocument);
        }

        System.out.println("Enter aadhar card link");
        String aadharCard = scanner.nextLine();
        if (!aadharCard.isEmpty()) {
            KycDocument kycDocument = KycDocument.createKyc(KycDocumentType.AADHAR_CARD, panCard);
            kycDocumentList.add(kycDocument);
        }

        System.out.println("Enter passport link");
        String passport = scanner.nextLine();
        if (!passport.isEmpty()) {
            KycDocument kycDocument = KycDocument.createKyc(KycDocumentType.PASSPORT, panCard);
            kycDocumentList.add(kycDocument);
        }

        return kycDocumentList;
    }

    /**
     * process withdrawal/deposit
     */
    private void processWithdrawalDeposit() {
        System.out.println("Please enter your account number");
        String accountNo = scanner.nextLine();

        try {
            Account account = AccountRepository.getAccount(accountNo);

            System.out.println("Please select below Option");
            System.out.println("1. Withdrawal");
            System.out.println("2. Deposit");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    processWithdraw(account);
                    break;
                case 2:
                    processDeposit(account);
                    break;
                default:
                    System.out.println("Invalid input!!! Please enter valid input");
                    break;
            }
        } catch (InvalidAccountNumberException e) {
            System.out.println("Invalid Account number provided!!! Please try again...");
        }
    }

    /**
     * process withdrawal
     *
     * @param account Account object
     */
    private void processWithdraw(Account account) {
        System.out.println("Enter the amount to withdraw");
        double amount = scanner.nextDouble();

        try {
            double balance = account.withdraw(amount);
            System.out.println("Balance left in account " + balance);
        } catch (InsufficientBalanceException e) {
            System.out.println("Withdraw terminated!!! Minimum balance should be 500 in the account!!! Please try again");
        }
    }

    /**
     * process deposit
     *
     * @param account Account object
     */
    private void processDeposit(Account account) {
        System.out.println("Please choose below option");
        System.out.println("1. Cash Deposit");
        System.out.println("2. Cheque Deposit");
        int input = scanner.nextInt();

        TransactionType transactionType = TransactionType.CASH_DEPOSIT;
        int chequeNo = 0;
        String panCardNumber = null;

        switch (input) {
            case 1:
                transactionType = TransactionType.CASH_DEPOSIT;
                break;

            case 2:
                transactionType = TransactionType.CHEQUE_DEPOSIT;
                System.out.println("Enter cheque number");
                chequeNo = scanner.nextInt();
                break;

            default:
                System.out.println("Invalid input!!! Please try again...");
                break;
        }

        System.out.println("Enter the amount to deposit");
        double amount = scanner.nextDouble();

        if (account.isPanCardRequired(amount)) {
            System.out.println("Enter pan card number");
            panCardNumber = scanner.nextLine();
        }

        try {
            double balance = account.deposit(transactionType, amount, chequeNo, panCardNumber);
            System.out.println("Deposit successful. Available balance is " + balance);

        } catch (IllegalArgumentException e) {
            System.out.println("Pan card is required for amount greater than " + account.getPanCardBalance());
        }
    }

    /**
     * View transaction history
     */
    private void processViewTransactionHistory() {
        System.out.println("Please enter your account number");
        String accountNo = scanner.nextLine();

        try {
            Account account = AccountRepository.getAccount(accountNo);
            account.displayTransactionDetails();
        } catch (InvalidAccountNumberException e) {
            System.out.println("Invalid Account number provided!!! Please try again...");
        }
    }
}