package com.ininbank.domain;

import java.time.LocalDateTime;

/**
 * Created by Deepak on 23/3/16.
 * This represents the every account in system.
 */

abstract public class Account {
    private int accountNumber;
    private LocalDateTime created;

    /**
     * Initialize the Accoutn object with account number and created date and time.
     * @param accountNumber is unque number of individual account
     * */
    public Account(int accountNumber){
        this.accountNumber = accountNumber;
        this.created = LocalDateTime.now();
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}
