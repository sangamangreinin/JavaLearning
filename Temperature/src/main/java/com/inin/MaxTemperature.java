package com.inin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by root on 24/3/16.
 *
 */
public class MaxTemperature {
    public static void main(String[] args) throws IOException {
        Map<String,List<String>> higestTemperatureYear  = new HashMap<String, List<String>>();

        File file = new File("src/main/resources");
        File[] files = file.listFiles();

        for (File file1 : files){
            BufferedReader reader = new BufferedReader(new FileReader(file1));
            String line =  reader.readLine();
            while (line != null){

            }
        }


    }
}
