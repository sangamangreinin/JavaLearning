package com.inin.domain;

import java.io.*;
import java.util.*;

/**
 * Created by root on 24/3/16.
 *
 */
public class MaxTemperature {
    public static void main(String[] args) throws IOException {

        File file = new File("src/main/resources/temperature/");
        File[] files = file.listFiles();

        Map<Integer, Integer> yearTempMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < files.length; i++) {

            if (files[i].isFile()) {
                System.out.println("inside if ");
                String tempLine;
                int maxTemperature = 0;
                int maxTemperatureStation = 0;

               // System.out.println(files[i].getPath());// getName());

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

                       // System.out.println(station + " || " + year + " || " + temp);

                        if (maxTemperature < temperature) {
                            maxTemperature = temperature;
                            maxTemperatureStation = station;
                            System.out.println(maxTemperature + " || " + maxTemperatureStation);

                            if (yearTempMap.containsKey(year)) {
                                if (temperature > yearTempMap.get(year)  ) {
                                    yearTempMap.put(year, temperature);
                                }
                            } else {
                                yearTempMap.put(year, temperature);
                            }
                        }
                        //System.out.println(maxTemp + " || " + maxTempStation);
                    }
                }

                System.out.println(yearTempMap);

                String maxTempAndStation = "Max Temperature : " + maxTemperature + "  Max Station : " + maxTemperatureStation + "\n";

            }
        }


        /*for (File file1 : files){
            BufferedReader reader = new BufferedReader(new FileReader(file1));
            String line =  reader.readLine();
            while (line != null){

            }
        }
*/

    }
}
