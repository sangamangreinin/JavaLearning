package com.inin.utility;

import com.inin.exceptions.InvalidInputException;

import java.io.File;
import java.io.IOException;

/**
 * Created by Deepak on 2/4/16.
 * This class used for utility functions need to perform service functionality.
 */

public class Utility {

    public static int count = 0;
    public static int generateUserId(){
        return ++count;
    }

    public static boolean validateString(String value){
        if(value == null || value.equals(""))
            return false;
        return true;
    }



    /**
     * This method is used to check directory is exist or not,
     * if exists return true.
     * If not create the directory and return the true if created success. */
    public static boolean createDirectory(String directroyPath) {
        File directory = new File(directroyPath);
        if (directory.exists()){
            return true;
        }
        else {
            if(directory.mkdirs())
                return true;
            else
                return false;
        }
    }


    /**
     * To check file and create if not present*/
    public static File createFile(String dirctory, String nameOfFile) {
        File file = null;
        if (createDirectory(dirctory)) {
            try {
                file = new File(dirctory + nameOfFile);
                if (!file.exists())
                    file.createNewFile();
            } catch (IOException e) {
                System.out.println("File not found!");
            }
        }
        return file;
    }

}