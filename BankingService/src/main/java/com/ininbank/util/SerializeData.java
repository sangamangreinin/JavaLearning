package com.ininbank.util;

import com.ininbank.domain.Customer;
import com.ininbank.domain.CustomerAccount;
import com.ininbank.domain.Document;
import com.ininbank.domain.Transaction;
import com.ininbank.solids.SystemConstants;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by root on 23/3/16.
 */
public class SerializeData {

    public boolean serializeAccount(CustomerAccount customerAccount){
        File accountFile = chekcSerilisedFile(SystemConstants.ACCOUNT_SER_FILE_PATH, customerAccount.getAccountNumber());
        return serialisedObject(customerAccount, accountFile, false);
    }

    public boolean serializeTransaction(Transaction transaction){
        File transactionFile = chekcSerilisedFile(SystemConstants.TRANSACTION_SER_FILE_PATH, transaction.getAccountNumber());
        return serialisedObject(transaction, transactionFile, true);
    }

    public void serializeDocument(Document[] documents, int accountNumber){
        File accountFile = chekcSerilisedFile(SystemConstants.DOCUMENTS_SER_FILE_PATH, accountNumber);
        for (int i =0; i< documents.length; i++){
            serialisedObject(documents[i], accountFile, true);
        }
    }

    public File chekcSerilisedFile(String directoryPath, int accountNumber){
        String accountFile = ""+accountNumber+".ser";
        return Utility.createFile(directoryPath, accountFile);
    }

    public boolean serialisedObject(Object object, File file, boolean append){
        ObjectOutputStream  oos = null;
        try {
            if(append){
                // append to the file.
                oos = new ObjectOutputStream(new FileOutputStream(file, true)){
                    protected void writeStreamHeader() throws IOException{
                        reset();
                    }
                };
            }
            else {
                // update whole file.
                oos = new ObjectOutputStream(new FileOutputStream(file));
            }

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


    public Map<Integer, CustomerAccount> readAllObjectsFromFile(File[] files){
        Map<Integer, CustomerAccount> tempMap = new ConcurrentHashMap<Integer, CustomerAccount>();

        ObjectInputStream ois = null;
        FileInputStream fis = null;

        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getName().substring(0, files[i].getName().lastIndexOf('.'));
            int accountNumber = Integer.parseInt(fileName);

            if (files[i].isFile()) {
                if(files[i].length() > 0){

                    try {
                        fis = new FileInputStream(files[i]);
                        ois = new ObjectInputStream(fis);

                        while(fis.available() > 0){
                            try {
                                CustomerAccount customerAccount = (CustomerAccount) ois.readObject();
                                tempMap.put(accountNumber, customerAccount);

                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    finally {
                        try {
                            ois.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        ;
                    }

                }
            }
        }

        return tempMap;
    }

}
