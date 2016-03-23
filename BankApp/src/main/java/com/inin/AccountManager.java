package com.inin;

import com.inin.model.Transaction;
import com.inin.service.AccountService;
import com.inin.util.InputReader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 23/3/16.
 */
public class AccountManager {
    AccountService accountService = new AccountService();

    /**
     * Collect all Information from user for open new account
     */
    public void openNewAccount(){
        System.out.println("Enter your Name");
        String name = InputReader.readString();

        System.out.println("Enter your address");
        String address = InputReader.readString();

        System.out.println("Enter your phone number");
        String phoneNumber = InputReader.readString();

        double balance = 0.0;
        do {
            System.out.println("Enter your Initial amount deposit into account( Amount must greater than 2000)");
            balance = InputReader.readDouble();
            if(balance < 2000)
                System.out.println("Balance must be greater than 2000.");
        }while (balance < 2000);

        Map<String,Map<String ,String>> kycDoc = collectKYC();
        String accId = accountService.openNewAccount(name,address,phoneNumber,balance,kycDoc);
        System.out.println("Your account is created with ID :"+accId);

    }



    /**
     * Collect KYC Document from User
     * @return Map All user provided KYC document
     */
    private Map collectKYC() {
        Map<String,Map<String ,String>> kycDoc = new HashMap<>();
        int kycOption = 0;
        System.out.println("Add KYC Document:-");
        do {
            if(kycOption != 0)
                System.out.println("Add Other KYC Document:-");
            System.out.println("1:-\t Pan Card");
            System.out.println("2:-\t Adhar Card");
            System.out.println("3:-\t Passport");
            System.out.println("4:-\t Quit");
            System.out.println("Enter your KYC Document option:");
            kycOption = InputReader.readInt();
            if(kycOption >= 1 && kycOption <= 3) {
                Map<String,String > doc = new HashMap<>();
                switch (kycOption) {
                    case 1:
                        System.out.println("Enter path of Pan Card Id:");
                        String panCardId = InputReader.readString();
                        System.out.println("Enter path of Pan Card Path:");
                        String panCardPath = InputReader.readString();
                        doc.put("panCardId",panCardId);
                        doc.put("panCardPath",panCardPath);
                        kycDoc.put("pan",doc);
                        break;
                    case 2:
                        System.out.println("Enter path of Adhar Card Id:");
                        String adharCardId = InputReader.readString();
                        System.out.println("Enter path of Adhar Card :");
                        String adharCardPath = InputReader.readString();
                        doc.put("adharCardId",adharCardId);
                        doc.put("adharCardPath",adharCardPath);
                        kycDoc.put("adhar",doc);
                        break;
                    case 3:
                        System.out.println("Enter path of Passport Id:");
                        String passportId = InputReader.readString();
                        System.out.println("Enter path of Passport:");
                        String passportPath = InputReader.readString();
                        doc.put("passportId",passportId);
                        doc.put("passportPath",passportPath);
                        kycDoc.put("passport",doc);
                        break;
                }
            }else if(kycOption != 4){
                System.out.println("Please Enter valid kyc option");
            }
        }while (kycOption != 4);
        return kycDoc;
    }

    /**
     * Take account id and withdraw amount from user and perform withdraw from user account
     */
    public void withdraw(){
        System.out.println("Enter account number :");
        String accId = InputReader.readString();
        System.out.println("Enter amount withdraw from account");
        double withdrawAmount =  InputReader.readDouble();
        accountService.withdraw(accId,withdrawAmount);
    }

    /**
     * Take account id and deposit amount from user and perform deposit into user account
     */
    public void deposit(){
        System.out.println("Enter account number :");
        String accId = InputReader.readString();
        System.out.println("Enter amount withdraw from account");
        double withdrawAmount =  InputReader.readDouble();
        if(withdrawAmount < 50000){
            accountService.deposit(accId,withdrawAmount,null);
        }else{
            System.out.println("Enter your Pan Card Number :");
            String panCardNumber = InputReader.readString();
            accountService.deposit(accId,withdrawAmount,panCardNumber);
            System.out.println("Amount "+withdrawAmount+ " successfully deposit into your account");
        }
    }

    /**
     * Display Account Details
     */
    public void viewAccountDetails(){
        System.out.println("Enter account number :");
        String accId = InputReader.readString();
        System.out.println(accountService.getAccountDetail(accId));
    }

    /**
     * Take account Id  and date range(option) from customer and display all transaction history
     */
    public void viewTransactionHistory(){
        System.out.println("Enter account number :");
        String accId = InputReader.readString();
        System.out.println("Transaction history type");
        System.out.println("1:-\t All");
        System.out.println("2:-\t Date Range");
        System.out.println("Please enter your option for view transaction history :");

        int viewType = InputReader.readInt();
        List<Transaction> transactions;
        if(viewType == 1)
            transactions = accountService.getAllTransaction(accId);
        else{
            System.out.println("Please Enter from Date(yyyy-mm-dd) :");
            String from = InputReader.readString();
            System.out.println("Please Enter to Date(yyyy-mm-dd) :");
            String to = InputReader.readString();
            try {
                LocalDateTime fromDate = LocalDateTime.parse(from+" 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                LocalDateTime toDate = LocalDateTime.parse(to+" 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                transactions = accountService.getAllTransaction(accId,fromDate,toDate);
            }catch (DateTimeParseException e){
                System.out.println("Please provide valid date format");
                return;
            }
        }
        if(transactions != null && transactions.size() > 0) {
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        }else{
            System.out.println("No transaction found");
        }
    }

    /**
     * Load Stored Account Details
     */
    public void loadStoredAccountDetails(){
        accountService.loadStoredAccountDetails();
    }
}
