/*

package com.inin;

import com.inin.util.Util;
import com.ininbank.util.Utility;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

*/
/**
 * Created by Deepak on 24/3/16.
 * Function to find out maximum temprature.
 *//*

public class test {
    public static void main(String[] args) throws IOException {

        String tempratureFolder = "src/main/resources/temprature/";
        String maxTempOutFolder = "src/main/resources/temprature/output/";

        File directory = new File(tempratureFolder);
        if (!directory.isDirectory()) {
            return;
        }

        String maxTempOutPutFile = "maxTempOutput";
        File outputFile = Util.createFile(maxTempOutFolder, maxTempOutPutFile);

        File[] files = directory.listFiles();
        for (int k = 0; k < files.length; k++) {

            if (files[k].isFile()) {

                String tempratureFile = tempratureFolder + "" + files[k].getName();

                FileReader fileReader = new FileReader(tempratureFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                FileWriter fileWriter = new FileWriter(outputFile, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                try {

                    String tempLine;
                    int maxTemp = 0;
                    int maxTempStation = 0;
                    Map<Integer, Integer> yearTempMap = new HashMap<>();

                    while ((tempLine = bufferedReader.readLine()) != null) {


                        String[] str = tempLine.split("\\|");
                        for (int i = 0; i < str.length; i++) {
                            int station = Integer.parseInt(str[0]);
                            int year = Integer.parseInt(str[1]);
                            int temp = Integer.parseInt(str[4]);

                            if (maxTemp < temp) {
                                maxTemp = temp;
                                maxTempStation = station;
                            }

                            if (yearTempMap.containsKey(year)) {
                                if (yearTempMap.get(year) < temp) {
                                    yearTempMap.put(year, temp);
                                }
                            } else {
                                yearTempMap.put(year, temp);
                            }

                        }
                    }

            */
/* Writing output to the file *//*

                    String maxTempAndStation = "Max Temp : " + maxTemp + "  Max Station : " + maxTempStation + "\n";
                    bufferedWriter.write(maxTempAndStation);

                    Set<Map.Entry<Integer, Integer>> entries = yearTempMap.entrySet();
                    for (Map.Entry entry : entries) {
                        String str = "Year : " + entry.getKey() + " Max Temprature : " + entry.getValue() + "\n";
                        bufferedWriter.write(str);
                    }
                    bufferedWriter.flush();


                    bufferedReader.close();
                    bufferedWriter.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } */
/*finally {
                try {
                    bufferedReader.close();
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*//*


            }
        }
    }
}
*/
