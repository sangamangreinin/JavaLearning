package com.inin.tms.util;

import java.io.*;
import java.util.Properties;

/**
 * Created by root on 23/3/16.
 * class uses for general purpose
 */
public class Util {
    public static BufferedReader consoleReader = null;

    /**
     * creating input stream reader
     * @return input stream reader InputStreamReader
     */
    public static BufferedReader getInputReader(){
        if(consoleReader == null)
            consoleReader = new BufferedReader(new InputStreamReader(System.in));
        return consoleReader;
    }

    /**
     * creating a new file
     * @param fileName string file name to create
     * @return file object
     */
    public static File createFile(String fileName, String path){
        File newFile = null;
        try {
            newFile = new File(path + fileName);
            newFile.createNewFile();
        }catch(IOException ie){
            ie.printStackTrace();
        }
        return newFile;
    }

    /**
     * write unique account into properties file
     * @param id unique account number
     */
    public static void writeAccountNumber(long id){
        try {
            File fileName = createFile("src/main/resources/properties", "account.properties");
            Properties prop = new Properties();
            prop.setProperty("accountNumber",String.valueOf(id+1));
            FileWriter writer = new FileWriter(fileName);
            prop.store(writer,"AccountNumber");           writer.close();
        }catch (FileNotFoundException fe){
            fe.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @return a unique account number
     */
    public static long getAccountNumber(){
        long accountNumber = 1000;
        try {
            File fileName = createFile("src/main/resources/properties", "account.properties");

            if(fileName.length()>0) {
                FileReader reader = new FileReader(fileName);
                Properties prop = new Properties();
                prop.load(reader);
                accountNumber = Integer.valueOf(prop.getProperty("accountNumber"));
                reader.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return accountNumber;
    }
}
