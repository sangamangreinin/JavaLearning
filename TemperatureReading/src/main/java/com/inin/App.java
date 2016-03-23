package com.inin;

import java.io.*;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        Map<String,List<String>> yearWithHighestTemperature = new HashMap<>();

        File file = new File("src/main/resources");
        File[] files = file.listFiles();

        for (File file1 : files){
            BufferedReader reader = new BufferedReader(new FileReader(file1));
            String line =  reader.readLine();
            while (line != null){
                String[] newWeatherRecords = line.split("\\|");
                if(yearWithHighestTemperature.containsKey(newWeatherRecords[1])){
                    List<String> oldWeatherRecords = yearWithHighestTemperature.get(newWeatherRecords[1]);
                    int oldTemperature = Integer.parseInt(oldWeatherRecords.get(1));
                    int newTemperature = Integer.parseInt(newWeatherRecords[4]);
                    if(oldTemperature < newTemperature){
                        yearWithHighestTemperature.put(newWeatherRecords[1], Arrays.asList(newWeatherRecords[0],newWeatherRecords[4]));
                    }
                }else{
                    yearWithHighestTemperature.put(newWeatherRecords[1], Arrays.asList(newWeatherRecords[0],newWeatherRecords[4]));
                }
                line = reader.readLine();
            }
        }

        yearWithHighestTemperature.forEach((year, value) -> {
            System.out.println("Year: " +year +" Station : "+value.get(0) + " Temperature :"+value.get(1));
        });
    }
}
