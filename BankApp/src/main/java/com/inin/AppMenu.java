package com.inin;

import com.inin.util.InputReader;

/**
 * Created by root on 23/3/16.
 */
public class AppMenu {
    public static AccountManager accountManager = new AccountManager();

    /**
     * Start BankApp
     */
    public void start(){
        accountManager.loadStoredAccountDetails();
        int userInput;
        do{
            displayMenu();
            userInput = InputReader.readInt();
            if(userInput >= 1 && userInput <= 6){
                processUserOption(userInput);
            }
            if(userInput == 7){
                System.out.println("You are quiting...\nGood bye!!!!!!");
                InputReader.closeReader();
            }
        }while (userInput != 7);
    }

    /**
     * Display menu option
     */
    public void displayMenu(){
        System.out.println("==============================================");
        System.out.println("1:-\t Open New Account.");
        System.out.println("2:-\t Withdraw from Account.");
        System.out.println("3:-\t Deposit into Account.");
        System.out.println("4:-\t Account Details.");
        System.out.println("5:-\t Transaction History.");
        System.out.println("6:-\t Add KYC Document.");
        System.out.println("7:-\t Quit.");
        System.out.println("==============================================");
        System.out.println("Enter your option :");
    }
    /**
     * Process entered user option
     * @param option Option selected by user
     */
    public static void processUserOption(int option){

        switch (option){
            case 1:
                accountManager.openNewAccount();
                break;
            case 2:
                accountManager.withdraw();
                break;
            case 3:
                accountManager.deposit();
                break;
            case 4:
                accountManager.viewAccountDetails();
                break;
            case 5:
                accountManager.viewTransactionHistory();
                break;
            case 6:
//                accountManager.displayAgentTickets();
                break;
            default:
                System.out.println("You Entered Wrong Option...");
        }
    }
}
