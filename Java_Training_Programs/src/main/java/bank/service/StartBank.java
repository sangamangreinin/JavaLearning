package bank.service;

import bank.domain.Account;
import bank.domain.Contants;
import bank.domain.Customer;
import bank.domain.Transaction;
import bank.serialization.AccountSerialization;
import bank.util.Util;

import java.util.*;

/**
 * Created by root on 23/3/16.
 *
 */
public class StartBank {

    Map<Long, Account> accountMasterMap = new HashMap<Long, Account>();
    Map<Long, Customer> customerMasterMap = new HashMap<Long, Customer>();
    List<Transaction> transactions = new ArrayList<Transaction>();
    Map<Long,List> accountTransactions = new HashMap<Long, List>();

    /**
     * taking user input
     */
    private void startApplication(){
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

        System.out.println("Enter Initial account opening balance : ");
        double initialBalance = Util.readDouble();

        // need to check initial balance condition
        //if(initialBalance < 2000)
            //throw new InSufficientBalanceException("Initial balance should be greater than 2000");

        // create a new customer
        Customer customer = new Customer(1,name,address,contact,kycType,kycPath);

        // create a new account
        Account newAccount = new Account(initialBalance,customer, new ArrayList<Transaction>());
        Util.writeAccountNumber(newAccount.accountNumber); // write account number into the file for automatic generation of the account number
        customerMasterMap.put(newAccount.accountNumber,customer);
        accountMasterMap.put(newAccount.accountNumber,newAccount); ///

        // Serialize the account data
        System.out.println("account number : " + newAccount.getAccountNumber());
        AccountSerialization.serialize(newAccount.getAccountNumber(),newAccount);

        System.out.println("in openNewAccount function : " + accountMasterMap);
    }

    /**
     * withdrawing the entered amount from the given account number
     * deserialize the account object with added transactions & updated balance
     */
    private void withDraw(){
        System.out.println("Enter your account Number : ");
        long accountNumber = Util.readInteger();

        // deserialize the data & check the entered account number is exist in the system
        Account account = AccountSerialization.deserialize(accountNumber);
        transactions = account.getTransaction(); // set previous transactions

        if(account != null){
            transactions = account.getTransaction(); // get existing transactions
            System.out.println("Enter withdrawl amount : ");
            double withdrawlAmount = Util.readDouble();
            double mainBalance = account.getBalance();
            if(mainBalance > withdrawlAmount) {
                // withdraw process starts here
                double availableBalance = mainBalance - withdrawlAmount;
                if(availableBalance> 500){
                    account.setBalance(availableBalance);
                    accountMasterMap.put(accountNumber,account);
                    Transaction transaction = new Transaction(accountNumber ,"withdraw","cash"); // create new transaction
                    transactions.add(transaction);
                    accountTransactions.put(accountNumber,transactions);
                    account.setTransaction(transactions);  // set all transactions into account object
                    transactions.clear();
                    // Serialize the account data
                    AccountSerialization.serialize(accountNumber,account);
                }
                else {
                    System.out.println("You can not withdraw " + withdrawlAmount + " min balance should be Rs. 500");
                }
            }
            else
            {
                System.out.println("Insufficient balance");
            }

        }
        else
            System.out.println("Entered account number " + accountNumber + " is not present in the system");
    }

    /**
     * depositing the entered amount into the given account number
     * deserialize the account object with added transactions & updated balance
     */
    private void deposit(){
        System.out.println("Enter your account Number : ");
        long accountNumber = Util.readInteger();

        // deserialize the data & check the entered account number is exist in the system
        Account account = AccountSerialization.deserialize(accountNumber);
        transactions = account.getTransaction(); // set previous transactions

        // check account number is exist in the system
        if(account != null) {
            transactions = accountTransactions.get(accountNumber); // get existing transactions
            System.out.println("Enter amount to deposit : ");
            Double depositAmount = Util.readDouble();
            Transaction transaction = new Transaction(accountNumber ,"deposit","cash");

            if(depositAmount > 50000) {
                System.out.println("Enter PAN number");
                String panNumber = Util.readString();
                transaction.setPanNumber(panNumber);
            }

            account.setBalance(account.getBalance() + depositAmount );
            accountMasterMap.put(accountNumber,account);
            transactions.add(transaction);
            accountTransactions.put(accountNumber,transactions);
            account.setTransaction(transactions);  // set all transactions into account object
            transactions.clear();
            AccountSerialization.serialize(accountNumber,account);
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

        // deserialize the data & check the entered account number is exist in the system
        Account account = AccountSerialization.deserialize(accountNumber);
        transactions = account.getTransaction(); // set previous transactions

        if(account != null){
            transactions = account.getTransaction(); // get existing transactions
            Iterator<Transaction> iterator = transactions.iterator();
            while (iterator.hasNext()) {
                Transaction transaction = iterator.next();
                System.out.println(transaction);
            }
        }
    }

    private void addKyc(){
        System.out.println("inte : " + Contants.INTEREST);
    }

    /**
     * Checking enetered account is exist in the system or not
     * @param accountNumber
     * @return Account object if account exist else null
     */
    public Account isAccountExist(long accountNumber){
        if(accountMasterMap.isEmpty()) return null;
        else {
            if (accountMasterMap.containsKey(accountNumber))
                return accountMasterMap.get(accountNumber);
            else
                return null;
        }
    }

    public static void main(String[] args) {
        StartBank bankObj = new StartBank();
        bankObj.startApplication();
    }
}
