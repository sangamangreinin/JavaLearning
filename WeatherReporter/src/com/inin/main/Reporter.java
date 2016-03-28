package com.inin.main;

import java.io.*;
import java.util.*;

/**
 * Created by lokesh on 23/3/16.
 */
public class Reporter {
    static final String path = "out/Recordings/";
    static final String path_statics = "out/Statistics/";
    public static void main(String[] args) {
        File directory = new File(path);
        if(!directory.exists()){
            System.out.println("Directory does not exists");
            System.exit(0);
        }

        File[] files = directory.listFiles();

        /*dataContainer: Key: Year Value: Object of DataContainer class holding year, temperature, station's list where temperature recorded*/
        Map<Integer,DataContainer> dataContainerList = new HashMap<>();

        for (File file: files) {
            if(file.length() == 0){
                continue;
            }
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
                String str = null;
                while ((str =bufferedReader.readLine() )!= null) {
                    String[] arr = str.split("\\|");
                    int station = Integer.parseInt(arr[0]);
                    int year = Integer.parseInt(arr[1]);
                    int temp = Integer.parseInt(arr[4]);
                    if(dataContainerList.containsKey(year)) {
                        DataContainer container = dataContainerList.get(year);
                        if(container.temperature < temp) {
                            container.temperature = temp;
                            container.stationList.clear();
                            container.stationList.add(station);
                        }
                        else if(container.temperature == temp) {
                            if(!container.stationList.contains(station))
                                container.stationList.add(station);
                        }
                        else
                            continue;
                    }
                    else {
                        DataContainer container = new DataContainer(temp, year, station);
                        dataContainerList.put(year,container);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        dataContainerList.forEach((k,v)->{
            File dir_statistics = new File(path_statics);
            if(!dir_statistics.exists()){
                System.out.println("Directory does not exists.");
                System.exit(0);
            }
            File statistics_file = new File(path_statics + "statistics.txt");
            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(statistics_file,true))) {
                bufferedWriter.write(v.toString());
            } catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(v);
        });
    }
}
