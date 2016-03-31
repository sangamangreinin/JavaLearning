package com.inin.domain;

import java.io.*;
import java.util.*;

/**
 * Created by root on 24/3/16.
 * Finding maximum temperature yearly basis.
 */
public class MaxTemperature {
    public static void main(String[] args) throws IOException {

        File file = new File("src/main/resources/temperature/");

        if(!file.isDirectory())
        {
            System.out.println("Directory is not present ");
            return;
        }

        File[] files = file.listFiles();
        Map<Integer, Integer> yearTempMap = new HashMap<Integer, Integer>();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/resources/output.txt"));

        if(files.length > 0) {
            for (int i = 0; i < files.length; i++) {

                if (files[i].isFile()) {
                    String tempLine;
                    int maxTemperature = 0;
                    int maxTemperatureStation = 0;

                    BufferedReader bufferedReader = new BufferedReader(new FileReader(files[i].getPath()));

                    int year = 0;
                    int temperature = 0;
                    int station = 0;

                    while ((tempLine = bufferedReader.readLine()) != null) {

                        String[] str = tempLine.split("\\|");
                        for (int j = 0; j < str.length; j++) {
                            station = Integer.parseInt(str[0]);
                            year = Integer.parseInt(str[1]);
                            temperature = Integer.parseInt(str[4]);

                            if (maxTemperature < temperature) {
                                maxTemperature = temperature;
                                maxTemperatureStation = station;
                            }
                            if (yearTempMap.containsKey(year)) {
                                if (temperature > yearTempMap.get(year)) {
                                    yearTempMap.put(year, temperature);
                                }
                            } else {
                                yearTempMap.put(year, temperature);
                            }
                        }
                    }

                    bufferedWriter.write("===================== File Name " + files[i].getPath() + " =====================\n");
                    String temperatureStationMax = "Maximum Temperature : " + maxTemperature + "  And station number : " + maxTemperatureStation + "\n";
                    bufferedWriter.write(temperatureStationMax);
                    bufferedWriter.write("Year wise Maximum temperature :: \n");
                    Set<Map.Entry<Integer, Integer>> data = yearTempMap.entrySet();
                    for (Map.Entry entry : data) {
                        String str = "Year : " + entry.getKey() + " Maximum temperature : " + entry.getValue() + "\n";
                        bufferedWriter.write(str);
                    }
                    bufferedWriter.flush();
                    bufferedReader.close();

                }
            }
        }
        else
            System.out.println("Given Directory is empty!!!");
        bufferedWriter.write("================================================================================\n");
        bufferedWriter.close();
    }
}
