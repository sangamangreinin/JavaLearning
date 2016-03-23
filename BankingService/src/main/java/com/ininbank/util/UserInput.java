package com.ininbank.util;

import com.ininbank.exceptions.UserInputExceptions;

import java.util.Scanner;

/**
 * Created by Deepak on 23/3/16.
 */
public class UserInput {
    public static Scanner scanner = new Scanner(System.in);

    /**
     * To get the integer parameter from user*/
    public static int acceptNumberInput(String message){
        boolean loop = true;
        int number = 0;
        String inputString = "";

        do{
            System.out.println(message);
            try {
                inputString = scanner.nextLine();
                number = Integer.parseInt(inputString);

                loop = false;
            }
            catch (NumberFormatException ne){
                System.out.println("Please give number input only");
            }
        }while (loop);

        return number;
    }

    /**
    * To get the String from user from console */
    public static String acceptStringInput(String message){
        String inputString = null;
        boolean loop = true;
        while (loop){
            System.out.println(message);
            inputString = scanner.nextLine();
            inputString = inputString.trim();
            if(inputString == null || inputString.equals("")){
                try {
                    throw new UserInputExceptions("Please give some proper value");
                } catch (UserInputExceptions e) {
                    System.out.println(e.getMessage());
                }
            }else{
                loop = false;
            }
        }
        return inputString;
    }

    /**
     * To get the float parameter from user*/
    public static float acceptAmountInput(String message){
        boolean loop = true;
        float number = 0;
        String inputString = "";

        do{
            System.out.println(message);
            try {
                inputString = scanner.nextLine();
                number = Float.parseFloat(inputString);

                loop = false;
            }
            catch (NumberFormatException ne){
                System.out.println("Please give number input only");
            }
        }while (loop);

        return number;
    }
}
