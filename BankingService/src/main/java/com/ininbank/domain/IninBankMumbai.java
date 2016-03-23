package com.ininbank.domain;

import com.ininbank.domain.Customer;
import com.ininbank.domain.Document;
import com.ininbank.exceptions.LessBalanceException;
import com.ininbank.solids.SystemConstants;
import com.ininbank.util.SerializeData;
import com.ininbank.util.UserInput;

/**
 * Created by root on 23/3/16.
 */
public class IninBankMumbai implements Banking{
    SerializeData serializeData = new SerializeData();

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
        System.out.println(customerAccount.toString());
        serializeData.serializeAccount(customerAccount);


    }

    Customer createCustomer(){

        String customerName = UserInput.acceptStringInput("Enter customer name");
        String customerAddress = UserInput.acceptStringInput("Enter customer address");
        int customerContact = UserInput.acceptNumberInput("Enter customer contact");

        return new Customer(customerName, customerAddress, customerContact);
    }


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


    Document getDocument(){
        String documentType = UserInput.acceptStringInput("Enter Document");
        String documentPath = SystemConstants.DOCUMENTS_SER_FILE_PATH;

        return new Document(documentPath, documentType);
    }
}
