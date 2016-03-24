package com.ininbank.domain;

import com.ininbank.exceptions.LessBalanceException;
import com.ininbank.solids.SystemConstants;
import com.ininbank.util.SerializeData;
import com.ininbank.util.UserInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Deepak on 23/3/16.
 * Responsible for providing all the functionality required.
 */
public class IninBankMumbaiStart implements Banking{
    SerializeData serializeData;
    static Map<Integer, CustomerAccount> accountHashMap = new ConcurrentHashMap<Integer, CustomerAccount>();
    static Map<Integer, List<Transaction>> transactionMap = new ConcurrentHashMap<Integer, List<Transaction>>();

    public IninBankMumbaiStart(){
        serializeData = new SerializeData();

        // Deserialization is not working properly so you can check the account details of currently created accounts only.
        //accountHashMap = InitSystem.initAccountMap(serializeData);
    }


    /**
     * Creating new account with customer details and documents */
    public void createAccount(){
        String panNo = null;
        Document[] documents;

        float initAmount = UserInput.acceptAmountInput("Enter Initial amount 2000 or more for account opening");
        if(initAmount < SystemConstants.MINIUM_BALANCE_ACCOUNT_OPEN) {
            try{
                throw new LessBalanceException("Minimum balance should be "+SystemConstants.MINIUM_BALANCE_ACCOUNT_OPEN+" , not less than that");
            }
            catch (LessBalanceException e){
                System.out.println(e.getMessage());
            }
        }

        if(initAmount > SystemConstants.REQUIRE_PAN_AMOUNT){
            panNo = UserInput.acceptStringInput("Enter Pan Number");
        }
        else panNo = "";

        Customer customer = createCustomer();
        documents = getTheDocuments();
        //CustomerAccount customerAccount = CustomerAccount.createAccount(initAmount, customer, documents);
        CustomerAccount customerAccount = new CustomerAccount(initAmount, customer, documents);
        int accoutnNumber = customerAccount.getAccountNumber();

        boolean isSerailizedAccount = serializeData.serializeAccount(customerAccount);

        if(documents != null){
            serializeData.serializeDocument(documents, accoutnNumber);
        }

        Transaction transaction = new Transaction(accoutnNumber,
                SystemConstants.TRANSACTION_DEPOSIT, initAmount, initAmount);
        boolean isSerrializedTransaction = serializeData.serializeTransaction(transaction);

        if(isSerailizedAccount && isSerrializedTransaction){
            accountHashMap.put(accoutnNumber, customerAccount);
            List transactionList = new ArrayList();
            transactionList.add(transaction);
            transactionMap.put(accoutnNumber, transactionList);
            System.out.println("Your account has been created! your Account Number : "+accoutnNumber);
        }

    }

    /**
     * Create customer with required details.
     * */
    Customer createCustomer(){

        String customerName = UserInput.acceptStringInput("Enter customer name");
        String customerAddress = UserInput.acceptStringInput("Enter customer address");
        int customerContact = UserInput.acceptNumberInput("Enter customer contact");

        return new Customer(customerName, customerAddress, customerContact);
    }


    /**
     * get the multiple documents.
     * */
    Document[] getTheDocuments(){
        Document[] documents = new Document[10];
        String option = UserInput.acceptStringInput("Are you providing documents? y : n");

        boolean loop = true;
        int i = 0;
        do {
            if (option.toLowerCase().equals("y")) {
                documents[i] = getDocument();
                i++;
                String option2 = UserInput.acceptStringInput("Do you want to submit more documents? y : n");
                if (!option2.toLowerCase().equals("y")) {
                    loop = false;
                }

            } else if(option.toLowerCase().equals("n")){
                documents = null;
                loop = false;
            }
            else {
                System.out.println("Enter proper input");
            }

        }while (loop);

        return documents;
    }


    /**
     * get the document object and return it.
     * */
    Document getDocument(){
        String documentType = UserInput.acceptStringInput("Enter Document");
        String documentPath = SystemConstants.DOCUMENTS_SER_FILE_PATH;

        return new Document(documentPath, documentType);
    }

    public void depositAmount(){
        String panNo = null;
        int accoutnNo = UserInput.acceptNumberInput("Enter account number");

        if(accountHashMap.containsKey(accoutnNo)){
            CustomerAccount customerAccount = accountHashMap.get(accoutnNo);
            float amount = UserInput.acceptAmountInput("Enter amount to deposit");

            if(amount >= SystemConstants.REQUIRE_PAN_AMOUNT){
                panNo = ""+UserInput.acceptStringInput("Enter pan number");
            }
            else panNo = "";

            customerAccount.deposit(amount);
            float currentBalnace = customerAccount.getBalance()+amount;
            serializeData.serializeAccount(customerAccount);

            Transaction transaction = new Transaction(accoutnNo,
                    SystemConstants.TRANSACTION_DEPOSIT, amount, currentBalnace);
            serializeData.serializeTransaction(transaction);

                accountHashMap.put(accoutnNo, customerAccount);
                List transactionList = transactionMap.get(accoutnNo);
                transactionList.add(transaction);
                transactionMap.put(accoutnNo, transactionList);
                System.out.println("Your amount deposit successful! your balance is : "+currentBalnace);
        }
        else {
            System.out.println("Invalid account number");
        }
    }


    /**
     * Withdraw amount from the account number given */
    public void withdrawAmout(){
        int accoutnNo = UserInput.acceptNumberInput("Enter account number");

        if(accountHashMap.containsKey(accoutnNo)) {
            CustomerAccount customerAccount = accountHashMap.get(accoutnNo);
            float amount = UserInput.acceptAmountInput("Enter amount to withdraw");

            float balance = customerAccount.getBalance();
            if ((balance - amount) > 500) {

                customerAccount.withdraw(amount);
                float currentBalnace = balance - amount;
                serializeData.serializeAccount(customerAccount);

                Transaction transaction = new Transaction(accoutnNo,
                        SystemConstants.TRANSACTION_WITHDRAW, amount, currentBalnace);
                serializeData.serializeTransaction(transaction);

                    accountHashMap.put(accoutnNo, customerAccount);
                    List transactionList = transactionMap.get(accoutnNo);
                    transactionList.add(transaction);
                    transactionMap.put(accoutnNo, transactionList);
                    System.out.println("Your amount withdraw successful! your balance is : " + currentBalnace);

            } else {
                System.out.println("Insufficient balance in your account.");
            }
        }
    }


    /**
     * View the details of account and account holder with provided doucments. */
    public void viewAccountDetails(){
        int accoutnNo = UserInput.acceptNumberInput("Enter account number");

        if(accountHashMap.containsKey(accoutnNo)) {
            CustomerAccount customerAccount = accountHashMap.get(accoutnNo);
            System.out.println(customerAccount);
        }
        else {
            System.out.println("Account number you entered is not present in system");
        }
    }


    /**
     * View the transaction details of all transaction on accounts */
    public void viewTransactions(){
        int accoutnNo = UserInput.acceptNumberInput("Enter account number");

        if(accountHashMap.containsKey(accoutnNo)) {
            System.out.println("inside");
            List<Transaction> transactionList = transactionMap.get(accoutnNo);
            if(transactionList.isEmpty())
                System.out.println("empty list.. ");

            for (Transaction transaction : transactionList){
                System.out.println("Herereeee");
                System.out.println(transaction.toString());
            }
        }
        else {
            System.out.println("Account number you entered is not present in system");
        }
    }

    /**
     * this will add the documents  to specific account number */
    public void addDocuments(){
        int accoutnNo = UserInput.acceptNumberInput("Enter account number");

        Document[] documents;
        if(accountHashMap.containsKey(accoutnNo)) {
            documents = getTheDocuments();
            if(documents != null){
                serializeData.serializeDocument(documents, accoutnNo);
            }
        }
        else {
            System.out.println("Account number you entered is not present in system");
        }
    }
}
