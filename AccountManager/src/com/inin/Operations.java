package com.inin;

import com.inin.constants.AccountConstants;
import com.inin.constants.DepositMode;
import com.inin.constants.KYCConstants;
import com.inin.constants.TransactionType;
import com.inin.constants.WithdrawalMode;
import com.inin.domain.Account;
import com.inin.domain.KYCDocs;
import com.inin.domain.TransactionHistory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by virendradubey on 23/3/16.
 */
public class Operations {

    Scanner scan = new Scanner(System.in);
    ObjectOutputStream out;
    ObjectInputStream in;
    public static final String BASE_FILE = "resources/accounts";

    Lock lock = new ReentrantLock();

    /**
     * adds new account in the system
     */
    public void addAccount(){
        System.out.println("Enter Account Holders Name: ");
        String name = scan.nextLine();

        System.out.println("Enter Address of the customer: ");
        String address = scan.nextLine();

        System.out.println("Enter starting balance: ");

        double amount = getAmount();

        if (amount < AccountConstants.MINIMUM_ACCOUNT_OPENING_BALANCE.getValue() ){
            throw new InputMismatchException("Initial balance must be at least Rs "
                    + AccountConstants.MINIMUM_ACCOUNT_OPENING_BALANCE.getValue());

        }

        //creating Account
        Account newAccount = Account.createAccount(name,address, amount);
        newAccount.createTransactionsList();
        //adding first transaction of account
        newAccount.addTransaction(TransactionType.CR, amount, DepositMode.CASH, amount);

        //write object in file

        File file = new File(BASE_FILE);
        if (!file.exists()){
            file.mkdirs();
        }

        if (!writeAccountObject(newAccount)){
            throw new InputMismatchException("Oops!! Something went wrong");
        }

    }

    public boolean writeAccountObject(Account newAccount) {
        File file = new File(BASE_FILE+"/"+newAccount.getAccountNumber()+".ser");

        try {
            out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(newAccount);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                System.out.println("Oops!! something went wrong!! ");
                e.printStackTrace();
            }
        }

    }

    public Account readAccountObject(String accountNo){
        File file = new File(BASE_FILE+"/"+accountNo+".ser");
        Account account = null;

        try {
            in = new ObjectInputStream(new FileInputStream(file));
            account = (Account) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                System.out.println("Oops!! something went wrong!! ");
                e.printStackTrace();

            }
        }
        return account;
    }

    private double getAmount() {

        boolean flag = false;
        double amount = 0;

        while (!flag){
            System.out.println("Enter amount : ");
            try {
                amount = scan.nextDouble();
                flag = true;
                scan.nextLine();
            }catch (InputMismatchException | NumberFormatException e){
                if (!flag) scan.nextLine();
                System.out.println("Only numbers with decimal are allowed!!");
            }
        }
        return amount;
    }


    private String getPan() {

        boolean flag = false;
        System.out.println("Enter PAN number: ");
        String panId = scan.nextLine();
        return panId;
    }

    /**
     * withdraws the amount from the account
     */
    public void withdrawAmount() {

        System.out.println("Enter Account Number: ");
        String accountNo = scan.nextLine();
        double amount = getAmount();

        //load account file
        try{
            lock.lock();
            Account account = readAccountObject(accountNo);
            if ((account.getBalance() - amount) < AccountConstants.MINIMUM_MAINTAIN_BALANCE.getValue() ){
                throw new InputMismatchException(amount+ "Insufficient Fund!! cannot withdraw "+amount);
            }
            account.withdrawAmount(amount);
            account.addTransaction(TransactionType.DR, amount, WithdrawalMode.CASH,account.getBalance());
            writeAccountObject(account);

        }finally {
            lock.unlock();
        }

    }

    /**
     * deposits the amount in the account
     */
    public void depositAmount(){

        System.out.println("Enter Account Number: ");
        String accountNo = scan.nextLine();
        double amount = getAmount();

        boolean panFlag = false;
        String panId = null;

        //load account file
        try{
            lock.lock();
            Account account = readAccountObject(accountNo);

            List<KYCDocs> kycs = account.getKYCs();
            boolean hasPan = false;

            if (kycs != null){
                for (KYCDocs k: kycs){
                    if(k.getKycType().equals(KYCConstants.PAN)){
                        hasPan = true;
                        break;
                    }
                }
            }

            if (!hasPan && amount > AccountConstants.MINIMUM_AMOUNT_WITHOUT_PAN.getValue()){
                System.out.println("PAN is required for this transaction");
                panId = getPan();
                panFlag = true;
            }

            if (panFlag)
                account.addInKYC(KYCConstants.PAN, panId);

            account.depositAmount(amount);
            account.addTransaction(TransactionType.CR, amount, DepositMode.CASH,account.getBalance());
            writeAccountObject(account);

        }finally {
            lock.unlock();
        }

        //print after deposit
        viewAccount(accountNo);

    }

    public void viewAccount(){
        System.out.println("Enter Account Number: ");
        String accountNo = scan.nextLine();
        //load account file
        loadAccountFile(accountNo);
    }

    private void loadAccountFile(String accountNo) {
        try{
            lock.lock();
            Account account = readAccountObject(accountNo);
            System.out.println("\tAccount details for "+account.getAccountNumber());
            System.out.println("Account No.: "+account.getAccountNumber());
            System.out.println("Customer Name: "+account.getCustomer().getName());
            System.out.println("Customer Address: "+account.getCustomer().getAddress());
            System.out.println("Current Balance: "+account.getBalance());
            System.out.println("Transaction Details: ");
            viewTransactionHistory(account);

        }finally {
            lock.unlock();
        }
    }

    private void viewTransactionHistory(Account account) {
        List<TransactionHistory> list = Collections.unmodifiableList(account.getTransactions());

        System.out.println("|\tDate\t\t|\tTYPE\t\t|\tMODE\t\t|\tAmount\t\t|\t\tBalance\t|");
        list.forEach(e->

                System.out.println("|\t"+e.getTime().toString()+"\t\t|\t"+e.getTransactionType().toString()+
                        "\t\t|\t"
                        +( e.getWithdrawMode() == null ? e.getDepositMode().toString() : e.getWithdrawMode().toString())
                        +"\t\t|\t"+e.getAmount()+"\t\t|\t\t"+e.getBalance()+"\t|"));
    }

    public void viewTransactionHistory(){
        String accountNo = scan.nextLine();
        //load account file
        Account account =  readAccountObject(accountNo);
        viewTransactionHistory(account);
    }

    public void viewAccount(String acc){
        loadAccountFile(acc);
    }

    private KYCConstants getKYCType(){

        boolean flag = false;
        double amount = 0;
        KYCConstants consts = null;

        while (!flag){
            System.out.println("Enter KYC document type: (PAN, AADHAR, PASSPORT)");
            String doc = scan.nextLine();
            try {
                switch (doc.toUpperCase()){
                    case "PAN": consts = KYCConstants.PAN; flag= true;
                        break;
                    case "AADHAR": consts = KYCConstants.AADHAR; flag= true;
                        break;
                    case "PASSPORT": consts = KYCConstants.PASSPORT; flag= true;
                        break;
                    default: throw new InputMismatchException("");
                }
            }catch (InputMismatchException | NumberFormatException e){
                System.out.println("Enter Proper KYC type");
            }
        }
        return consts;
    }

    public void addKYC() {

        System.out.println("Enter Account Number: ");
        String accountNo = scan.nextLine();

        KYCConstants kycType = getKYCType();
        System.out.println("Enter unique id: ");
        String id = scan.nextLine();

        try{
            lock.lock();
            Account account = readAccountObject(accountNo);
            account.addInKYC(kycType, id);
            writeAccountObject(account);
        }finally {
            lock.unlock();
        }

    }
}
