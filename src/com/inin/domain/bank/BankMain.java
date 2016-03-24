package com.inin.domain.bank;

import com.inin.domain.bank.util.ConsoleReader;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by leroy on 23/3/16.
 *
 * This is the main program for execution
 */
public class BankMain {

    private static int count = 0;

    private static final int MAX_TRY = 5;

    public static void main(String[] args) {

        ConsoleReader consoleReader = new ConsoleReader();  // Reader Class to get User Input

        Scanner scanner = new Scanner(System.in);   // used for scanning user input for menu purpose

        System.out.println("//===============================//");
        System.out.println("****** Welcome to ININ Bank ******");
        System.out.println("//===============================//");

        do {
            int option;
            try{
                do {
                    menu();
                    option = scanner.nextInt();
                    switch (option){
                        case 1:
                            consoleReader.createAccount();
                            break;

                        case 2:
                            consoleReader.withdrawDeposit();
                            break;

                        case 3:
                            consoleReader.viewAccountDetails();
                            break;

                        case 4:
                            consoleReader.viewHistory();
                            break;

                        case 5:
                            System.out.println("Temporarily out of service.");
                            break;

                    }

                }while (option > 0 && option <= 5);

                if (option > 5){
                    System.out.println("Thank you.!! \nVisit Again.");
                    scanner.close();
                    System.exit(0);
                }
            }
            catch (InputMismatchException In){
                System.out.println("Something went wrong. Try Again.");
                ++count;
            }
            catch (IllegalArgumentException Il){
                ++count;
                Il.printStackTrace();
            }
            finally {
                if (count == MAX_TRY){
                    System.out.println("\nMaximum In-correct try reached.\nThank you.!! \nVisit Again.");
                    scanner.close();
                    System.exit(1);
                }
            }
        }while (MAX_TRY <= 5);
    }

    public static void menu(){
        System.out.println("\n-----------------------------------\n" +
                "How can we help you today. ??");
        System.out.println("Choose from below options\n" +
                "1 - Open a new Account.\n" +
                "2 - Perform Withdraw / Deposit on an Account.\n" +
                "3 - View details of your Account.\n" +
                "4 - View transaction history of your Account.\n" +
                "5 - Add your KYC Document.\n" +
                "6 - Exit\n" +
                "-----------------------------------");
    }
}
