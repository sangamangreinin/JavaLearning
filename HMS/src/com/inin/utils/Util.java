package com.inin.utils;

/**
 * Created by virendradubey on 29/3/16.
 */
public class Util {
    /**
     * prints the menu for the use in application
     */
    public static final int TOP_MENU = 0;
    public static final int DOCTOR_SUB_MENU = 1;
    public static final int TOP_MENU_PATIENT = 2;
    public static final int LOGIN = 3;


    public static void displayMenu(int type){

        switch(type){
            case TOP_MENU_PATIENT:
                System.out.println("==== Program Menu ====");
                System.out.println("1.\tView Details\n" +
                        "2.\tView your appointments\n" +
                        "3.\tSchedule appointment with doctor\n" +
                        "0.\tQUIT (EXIT)\n"
                );
                System.out.println("Enter your option: ");
                break;
            case DOCTOR_SUB_MENU:

                break;

            case LOGIN:
                System.err.print("Login is required!\n");
                break;

            default:
            case TOP_MENU:
                System.out.println("==== Program Menu ====");

                System.out.println("1.\tManage Patients\n" +
                        "2.\tView Appointments\n" +
                        "3.\tView Chemists\n" +
                        "4.\tSend Mail of Prescription to Chemist\n" +
                        "0.\tQUIT (EXIT)\n"
                );
                System.out.println("Enter your option: ");
                break;

        }

    }
}
