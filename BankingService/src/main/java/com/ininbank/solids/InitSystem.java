package com.ininbank.solids;

import com.ininbank.domain.CustomerAccount;
import com.ininbank.exceptions.SystemExceptions;
import com.ininbank.util.SerializeData;
import com.ininbank.util.Utility;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by root on 23/3/16.
 */
public class InitSystem {

    static{
        initDirectories();
    }


    /**
     * this responsible for Creating directories in resources */
    public static void initDirectories(){
        String[] directories = {
                SystemConstants.ACCOUNT_SER_FILE_PATH,
                SystemConstants.TRANSACTION_SER_FILE_PATH,
                SystemConstants.DOCUMENTS_SER_FILE_PATH
            };

        for(int i=0; i <directories.length; i++){
            Utility.createDirectory(directories[i]);
        }
    }

    /** This method is used to get the initial account number of system.
     * normally its starts with 1 and increasing, on the basis of accounts files saved in accounts resources.*/
    public static int initAccountNumber() throws SystemExceptions {
        String accountFolder = SystemConstants.ACCOUNT_SER_FILE_PATH;
        int count = 0;
        if(Utility.createDirectory(accountFolder)) {
            File folder = new File(accountFolder);
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {

                    String fileName = listOfFiles[i].getName().substring(0, listOfFiles[i].getName().lastIndexOf('.'));
                    int temp = Integer.parseInt(fileName);
                    if(count < temp)
                        count = temp;
                }
            }

            return count + 1;
        }
        else {
            throw new SystemExceptions("There is some problem in initialize the system. Please contact system administrator.");
        }
    }

    /**
     * Ininitalizie the map on starup of system.*/
    public static Map<Integer, CustomerAccount> initAccountMap(SerializeData serializeData){
        File accountFolder = new File(SystemConstants.ACCOUNT_SER_FILE_PATH);
        File[] listOfFiles = accountFolder.listFiles();
        return serializeData.readAllObjectsFromFile(listOfFiles);
    }
}
