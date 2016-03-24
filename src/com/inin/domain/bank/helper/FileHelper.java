package com.inin.domain.bank.helper;

import com.inin.domain.bank.model.BankAccount;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by leroy on 23/3/16.
 *
 * does file related operations like serializing and de-serializing account files
 */
public class FileHelper {

    public static final String DIR = System.getProperty("user.dir")+"/src/com/inin/domain/bank/vault/";

    public boolean serialize(long fileName, BankAccount bankAccount){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(DIR+fileName+".ser"))){
            outputStream.writeObject(bankAccount);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public BankAccount deSerialize(String fileName){
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(DIR+fileName))){
            return  (BankAccount)objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<Integer,BankAccount> deSerializeAll(File obj){
        Map<Integer, BankAccount> bankAccountMap = new HashMap<>();
        for (File subFile : obj.listFiles()){
            BankAccount bankAccount = deSerialize(subFile.getName());

            bankAccountMap.put(bankAccount.getAccountNo(), bankAccount);
        }
        return bankAccountMap;

    }


}
