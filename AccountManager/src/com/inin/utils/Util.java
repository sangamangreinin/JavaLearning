package com.inin.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by virendradubey on 23/3/16.
 */
public class Util {

    private static final String FILE_LOCATION = "resources/account.properties";

    /**
     * displays the Menu to user for selection
     */
    public static void displayMenu(){
        System.out.println("==== Program Menu ====");
        System.out.println("1.\tOpen a new account\n" +
                "2.\tWithdraw from Account\n" +
                "3.\tDeposit to Account\n" +
                "4.\tView details of account\n" +
                "5.\tView transaction history of account\n" +
                "0.\tQUIT (EXIT)\n"
        );
        System.out.println("Enter your option: ");
    }

    /**
     * reads the account id from the file
     * @return value of property from properties file
     */
    public static Integer readAccountId() {
        Properties prop = new Properties();
        Integer id = 0;
        try {
            try(FileInputStream fs =  new FileInputStream(FILE_LOCATION)){
                prop.load(fs);
                id = new Integer(prop.getProperty("accountid"));
            }
        } catch (IOException e) {
            System.out.println("Error in loading account initializer");
        }
        return id;
    }

    /**
     * writes new account id to properties file
     * @param value new value to be added in the properties file
     */
    public static void writeAccountId(String value){
        Properties prop = new Properties();
        Integer id = 0;
        try {
            try(FileInputStream fs =  new FileInputStream(FILE_LOCATION)){
                prop.load(fs);
                prop.setProperty("accountid", String.valueOf(value));
            }
        } catch (IOException e) {
            System.out.println("Error in loading account initializer");
        }
    }
}
