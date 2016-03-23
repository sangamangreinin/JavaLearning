package com.inin.bank.domain;

import java.io.*;

/**
 * Created by root on 23/3/16.
 */
public class FileOperation {

    public static final String FILE_PATH = "/home/BankAccount/";

    public static CustomerAccount readObj(int accNo) {

        CustomerAccount customerAccount = null;
        try {
            FileInputStream fin = new FileInputStream(FILE_PATH + accNo);
            ObjectInputStream oos = new ObjectInputStream(fin);
            customerAccount = (CustomerAccount) oos.readObject();
            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return customerAccount;
    }

    /**
     *
     * @param customerAccount
     */
    public static void writeObj(CustomerAccount customerAccount) {
        try {
            FileOutputStream fout = new FileOutputStream(FILE_PATH + customerAccount.getAccNo());
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(customerAccount);
            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     */
    public static void getload() {
        File file = new File(FILE_PATH);
        if (file.exists()) ;
        {
            String[] fileList = file.list();
            for (String name : fileList) {
                if (name != null || !name.isEmpty()) {
                    int accNo = Integer.parseInt(name);
                    BankService.accHashMap.put(accNo, readObj(accNo));
                }
            }
        }
    }
}
