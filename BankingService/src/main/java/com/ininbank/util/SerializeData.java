package com.ininbank.util;

import com.ininbank.domain.CustomerAccount;
import com.ininbank.domain.Transaction;
import com.ininbank.solids.SystemConstants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by root on 23/3/16.
 */
public class SerializeData {

    public boolean serializeAccount(CustomerAccount customerAccount){
        File accountFile = chekcSerilisedFile(SystemConstants.ACCOUNT_SER_FILE_PATH, customerAccount.getAccountNumber());
        return serialisedObject(customerAccount, accountFile);
    }


    public File chekcSerilisedFile(String directoryPath, int accountNumber){
        String accountFile = ""+accountNumber+".ser";
        return Utility.createFile(directoryPath, accountFile);
    }

    public boolean serialisedObject(Object object, File file){
        ObjectOutputStream  oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(object);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public boolean serializeTransaction(Transaction transaction){
        File transactionFile = chekcSerilisedFile(SystemConstants.TRANSACTION_SER_FILE_PATH, transaction.getAccountNumber());
        return serialisedObject(transaction, transactionFile);
    }
}
