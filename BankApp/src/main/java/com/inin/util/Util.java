package com.inin.util;

import com.inin.model.Account;

import java.io.*;

/**
 * Created by root on 23/3/16.
 */
public class Util {
    /**
     * Validate String for null and empty
     * @param str String which is validated
     * @return boolean
     */
    public static boolean isValidString(String str){
        return str != null && !str.isEmpty();
    }

    /**
     * Serialize Account object into specified file
     * @param account Account object which is to be serialize
     */
    public static void serialize(Account account) {
        ObjectOutputStream oos = null;
        try{
            File file = new File("src/main/resources/account/account_"+account.getAccId()+".ser");
            if(!file.exists()){
                file.createNewFile();
            }
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(account);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (oos != null)
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * Deserialize Account object from file
     * @return Account
     */
    public static Account deserialize(long accId) {
        Account account = null;
        File file = new File("src/main/resources/account/account"+accId+".ser");
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            account = (Account) ois.readObject();
        }catch (EOFException e){
//            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return account;
    }
}
