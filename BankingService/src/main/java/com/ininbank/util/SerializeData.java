package com.ininbank.util;

import com.ininbank.domain.CustomerAccount;
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
        File accountFile = chekcAccountFile(customerAccount.getAccountNumber());
        ObjectOutputStream  oos;

        try {
            oos = new ObjectOutputStream(new FileOutputStream(accountFile));
            oos.writeObject(customerAccount);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


    public File chekcAccountFile(int accountNumber){
        String accountFile = ""+accountNumber+".ser";
        return Utility.createFile(SystemConstants.ACCOUNT_SER_FILE_PATH, accountFile);
    }
}
