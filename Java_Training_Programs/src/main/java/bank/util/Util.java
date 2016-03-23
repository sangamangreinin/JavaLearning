package bank.util;

import java.io.*;
import java.util.Properties;

/**
 * Created by root on 23/3/16.
 * class uses for general purpose
 */
public class Util {
    public static BufferedReader consoleReader = null;

    /**
     * taking user input as integer
     * @return integer format number
     */
    public static int readInteger(){
        int userInput = 0;

        try{
            BufferedReader consoleReader = getInputReader();
            userInput = Integer.parseInt(consoleReader.readLine());
        }
        catch (NumberFormatException ne) {
            System.out.println("Invalid Number!!!");
        }
        catch (IOException ie) {
            ie.printStackTrace();
        }
        return userInput;
    }

    /**
     * taking user input as double
     * @return double format number
     */
    public static double readDouble(){
        double userInput = 0;

        try{
            BufferedReader consoleReader = getInputReader();
            userInput = Double.parseDouble(consoleReader.readLine());
        }
        catch (NumberFormatException ne) {
            System.out.println("Invalid Number!!!");
        }
        catch (IOException ie) {
            ie.printStackTrace();
        }
        return userInput;
    }

    /**
     *  taking user input as string
     * @return string
     */
    public static String readString(){
        String userInput = null;
        try{
            BufferedReader consoleReader = Util.getInputReader();
            userInput = consoleReader.readLine();
        }catch(IOException ie) {
            ie.printStackTrace();
        }

        return userInput;
    }

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
    public static File createFile(String fileName){
        File newFile = null;
        try {
            newFile = new File("src/main/data/" + fileName);
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
            File fileName = createFile("account.properties");
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
            File fileName = createFile("account.properties");

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
