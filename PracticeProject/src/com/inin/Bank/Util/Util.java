package com.inin.Bank.Util;

import com.inin.Bank.Domain.Account;

import java.io.*;
import java.util.HashMap;

/**
 * Created by evansbelly on 23/3/16.
 */
public class Util {
    /**
     * Serializes the account object
     * @param account
     * @param accountNo
     */
    public static void serializeAccount(Account account, long accountNo) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("ININ" + accountNo + ".ser"));
            objectOutputStream.writeObject(account);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserialize the .ser files all at once.
     * @return HashMap that holds key as the account no. and value as the account object
     */
    public static HashMap<Long, Account> deserializeAccounts() {
        HashMap<Long, Account> dserList = new HashMap<>();
        Account account = null;
        String temp = "";
        try {
            String myCurrentDir = System.getProperty("user.dir");
            File folder = new File(myCurrentDir);
            for (File file : folder.listFiles()) {
                temp = file.getName();
                if (file.isFile()) {
                    if ((temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("ser"))
                        System.out.println(temp);
                    if (temp.contains(".ser")) {
                        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(temp));
                        account = (Account) objectInputStream.readObject();
                        dserList.put(account.getAccountNo(), account);
                    }
                }
            }
            System.out.println(account);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dserList;
    }
}
