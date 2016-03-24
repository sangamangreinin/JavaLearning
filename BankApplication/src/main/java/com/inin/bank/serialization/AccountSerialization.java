package com.inin.bank.serialization;

import com.inin.bank.domain.Account;
import com.inin.bank.util.Util;

import java.io.*;
import java.util.*;

public class AccountSerialization {

    /**
     * Serializing the account object
     * @param account Account Object to serialize
     */
    public static void serialize(Account account)
    {
        ObjectOutputStream oos = null;
           try {
               String fileName = account.getAccountNumber() + ".ser";
               File file = Util.createFile("src/main/resources/account", fileName);
               if(file.length()<=0) {
                   oos = new ObjectOutputStream(new FileOutputStream(file));
               }
               else {
                   // if blnAppend is true in new FileOutputStream(fileName, blnAppend),
                   // then output will be appended to the existing content of the file. If false, file will be overwritten.
                   oos = new ObjectOutputStream(new FileOutputStream(file)) {
                       protected void writeStreamHeader() throws IOException {
                           reset();
                       }
                   };
               }
               oos.writeObject(account);
           }catch(IOException ie){
               ie.printStackTrace();
           }
    }

    /**
     * deserializing the account object
     * @param accountNumber account number
     * @return Account object
     */
    public static Account deserialize(long accountNumber){
        Map<Long, Account> ticketsData = new HashMap<Long, Account>();
        Account account = null;
        try{
            String fileName = accountNumber + ".ser";
            File file = Util.createFile("src/main/resources/account", fileName);
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            account = (Account) ois.readObject();
        }
        catch (EOFException eof) {
            eof.printStackTrace();
        }
        catch(IOException ie) {
            ie.printStackTrace();
        }
        catch(ClassNotFoundException ce){
            ce.printStackTrace();
        }
        return account;
    }
}
