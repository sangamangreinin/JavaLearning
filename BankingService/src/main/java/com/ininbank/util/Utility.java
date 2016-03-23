package com.ininbank.util;

import com.ininbank.solids.SystemConstants;

import java.io.File;
import java.io.IOException;

/**
 * Created by root on 23/3/16.
 */
public class Utility {

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
