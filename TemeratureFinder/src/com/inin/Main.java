package com.inin;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by virendradubey on 23/3/16.
 */
public class Main {

    static Map<Integer, String> entries;

    public static void main(String[] args) throws IOException {
        System.out.println("Temperature Finder");

        //reading temperature file
        try(BufferedReader fIn = new BufferedReader(new FileReader("resources/temperatureRecord.txt"))){

            String line;
            entries = new TreeMap<>();
            int i = 0;

            System.out.println(entries);

            while ( (line = fIn.readLine())!=null){
                entries.put(i++, line);
            }

            System.out.println(entries);
        }

        processEntries();


        //output the maximum temperature in the file
        try(BufferedWriter fOut = new BufferedWriter(new FileWriter("resources/output.txt"))){
            fOut.write("output");
            fOut.flush();
        }


    }

    private static void processEntries() {

        entries.forEach((k,v)->{
            String[] values = v.split("|");
            
        });
    }
}
