package com.inin.util;

import java.io.File;
import java.io.IOException;

/**
 * Created by root on 23/3/16.
 * class uses for general purpose
 */
public class Util {
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
}
