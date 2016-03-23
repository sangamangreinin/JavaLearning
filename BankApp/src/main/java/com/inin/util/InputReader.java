package com.inin.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * Created by root on 23/3/16.
 */
public class InputReader {

        public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        /**
         * Used for read int from console
         * @return int integer value enter by user
         */
        public static int readInt(){
            int userInput = -1;
            try {
                userInput = Integer.parseInt(readString());
            } catch (NumberFormatException e) {
                System.out.println("Only integer value is allowed");
            }
            return userInput;
        }

        /**
         * Used for read int from console
         * @return int integer value enter by user
         */
        public static double readDouble(){
            double userInput = 0.0;
            try {
                userInput = Double.parseDouble(readString());
            } catch (NumberFormatException e) {
                System.out.println("Only integer value is allowed");
            }
            return userInput;
        }

        /**
         * Used to read String from console
         * @return String
         */
        public static String readString(){
            String userInput = null;
            try {
                userInput = reader.readLine();
            } catch (IOException ioe){
                ioe.printStackTrace();
            }
            return userInput;
        }

        /**
         * Close BufferedReader
         */
        public static void closeReader(){
            try {
                if(reader != null) {
                    reader.close();
                    reader = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
