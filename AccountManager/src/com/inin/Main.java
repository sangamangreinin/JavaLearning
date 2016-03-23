package com.inin;

import com.inin.constants.AccountConstants;
import com.inin.utils.Util;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by virendradubey on 23/3/16.
 * Main class to start the application
 */
public class Main {

    static Scanner scan;

    public static void main(String[] args) {

        System.out.println("\tWelcome to BankManager=====================");
        System.out.println("Note: Account Numbers are in Format: SB00001, SB00002 and so on...");
        int ch = 0;
        scan = new Scanner(System.in);
        String repeat;
        boolean flushFlag = false;
        Operations operations = new Operations();


        do{
            Util.displayMenu();
            try{
                flushFlag = false;
                ch = scan.nextInt();
                scan.nextLine();
                flushFlag = true;
                performOperation(operations, ch);

            }catch (InputMismatchException e){

                if (!flushFlag) scan.nextLine();

                System.out.println("Invalid choice. "+e.getMessage()+". Try again? (y/n)");
                try{
                    repeat = scan.nextLine();

                    System.out.println(repeat);
                    if (repeat.equals("y") || repeat.equals("Y")) {
                        ch =-1;
                        continue;
                    } else{
                        System.out.println("Thank you for using the Application. Bye!!");
                        ch=0;
                        break;
                    }
                }catch (InputMismatchException e1){
                    System.out.println("Invalid choice. Quiting Application...");
                    ch =0;
                    break;
                }
            }


        }while (ch!=0);
        System.out.println("Out of loop");

    }

    private static void performOperation(Operations operations, int ch) {


        switch (ch){
            case 0:
                System.out.println("Thank you for using the Application. Bye!!");
                break;
            case 1:
                operations.addAccount();
                break;
            case 2:
                operations.withdrawAmount();
                break;
            case 3:
                operations.depositAmount();
                break;
            case 4:
                operations.viewAccount();
                break;
            case 5:
                operations.viewTransactionHistory();
                break;
            default:
                throw new InputMismatchException("Allowed values are from 0-5");
        }
    }
}
