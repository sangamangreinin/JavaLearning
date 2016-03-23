package com.inin.domain;

import com.inin.constants.DepositMode;
import com.inin.constants.TransactionType;
import com.inin.constants.WithdrawalMode;

import java.io.Serializable;

/**
 * Created by virendradubey on 23/3/16.
 * Base interface of Account containing contracts to be implemented by the implementer
 */
public interface AccountBase extends Serializable{

    long serialVersionUID = 1;
    /**
     * interestRate of the account
     */
    float interestRate = 6.5f;
    /**
     * duration for calculation of the interest
     */
    int duration = 12;

    double calculateInterest();

    void addTransaction(TransactionType dr, double value, DepositMode d, double total);

    void addTransaction(TransactionType dr, double value, WithdrawalMode w, double total);
}
