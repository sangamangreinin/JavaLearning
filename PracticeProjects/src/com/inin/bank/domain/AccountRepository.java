package com.inin.bank.domain;

import com.inin.bank.exception.InvalidAccountNumberException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 23/3/16.
 */
public class AccountRepository {
    /**
     * temporary storage(in memory implementation)
     */
    static private Map<String, Account> stringAccountMap = new HashMap<>();

    /**
     * add data to temporary storage
     *
     * @param account Account object to add
     */
    public static void put(Account account) {
        stringAccountMap.put(account.getAccountNo(), account);
    }

    /**
     * get account object as per account number provided
     *
     * @param accountNo Account number to get Account object
     * @return Account object
     * @throws InvalidAccountNumberException when the account number is not valid
     */
    public static Account getAccount(String accountNo) throws InvalidAccountNumberException {
        Account account = stringAccountMap.get(accountNo);
        if (account == null) {
            throw new InvalidAccountNumberException("Account number not found!!!");
        }
        return account;
    }

    /**
     * load all the data on startup from the data directory which was previously being serialized
     */
    public static void loadDataOnStartup() {
        File file = new File(FileIOUtil.getDestinationPath());
        File[] files = file.listFiles();
        for (File file1 : files) {
            Account account = (Account) FileIOUtil.deserialize(file1.getName());
            stringAccountMap.put(account.getAccountNo(), account);
        }
    }
}
