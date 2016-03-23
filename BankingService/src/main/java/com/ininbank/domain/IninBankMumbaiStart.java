package com.ininbank.domain;

import com.ininbank.exceptions.LessBalanceException;
import com.ininbank.solids.SystemConstants;
import com.ininbank.util.SerializeData;
import com.ininbank.util.UserInput;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by root on 23/3/16.
 */
public class IninBankMumbaiStart implements Banking{
    SerializeData serializeData;
    static ConcurrentHashMap<Integer, CustomerAccount> accountHashMap = null;

    public IninBankMumbaiStart(){
        serializeData = new SerializeData();
    }
    /**
     * Creating new account with customer details and documents */
    public void createAccount(){
        String panNo = null;
        Document[] documents;

        float initAmount = UserInput.acceptAmountInput("Enter Initial amount to deposit");
        if(initAmount < SystemConstants.MINIUM_BALANCE_ACCOUNT_OPEN) {
            throw new LessBalanceException("Minimum balance should be "+SystemConstants.MINIUM_BALANCE_ACCOUNT_OPEN+" , not less than that");
        }

        if(initAmount > SystemConstants.REQUIRE_PAN_AMOUNT){
            panNo = UserInput.acceptStringInput("Enter Pan Number");
        }

        Customer customer = createCustomer();
        documents = getTheDocuments();
        CustomerAccount customerAccount = CustomerAccount.createAccount(initAmount, customer, documents);
        int accoutnNumber = customerAccount.getAccountNumber();

        boolean isSerailizedAccount = serializeData.serializeAccount(customerAccount);

        Transaction transaction = createTransaction(accoutnNumber,
                SystemConstants.TRANSACTION_DEPOSIT, initAmount, 0);
        boolean isSerrializedTransaction = serializeData.serializeTransaction(transaction);

        if(isSerailizedAccount && isSerrializedTransaction){
            accountHashMap.put(accoutnNumber, customerAccount);
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

    public boolean depositAmount(){

        return false;
    }

    /**
     * create the transaction on account number and the amount.*/
    public Transaction createTransaction(int accountNumber, String type, float amtDeposit, float balance){
        return new Transaction(accountNumber, type, amtDeposit, balance);
    }
}
