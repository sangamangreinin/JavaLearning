package com.inin.Bank;

import com.inin.Bank.Domain.Account;
import com.inin.Bank.Domain.Customer;
import com.inin.Bank.Domain.KycInfo;
import com.inin.Bank.Domain.Transaction;
import com.inin.Bank.Exceptions.BadDataException;
import com.inin.Bank.Server.BankService;
import com.inin.Bank.Util.Util;

import java.io.*;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by evansbelly on 23/3/16.
 */
public class BankMenu {

    static HashMap<Long, Account> accountsList = Util.deserializeAccounts();

    /**
     * Menu class
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean autoRun = true;
        do {
            System.out.println("Select an operation to perform : \n" +
                    "1. Open a new account. \n" +
                    "2. Perform withdrawl/deposit on an account. \n" +
                    "3. View the details of my account.\n" +
                    "4. View the transaction history of my account. \n" +
                    "5. Add my KYC documents. \n" +
                    "0. Exit");
            int selection = scanner.nextInt();
            new BankService().optionSelection(scanner, selection, accountsList);
        }
        while (autoRun);
    }
}
