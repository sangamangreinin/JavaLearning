package com.inin.Bank;

import com.inin.Bank.domain.Account;
import com.inin.Bank.domain.Address;
import com.inin.Bank.domain.Customer;

/**
 * Created by root on 23/3/16.
 */
public class RunAccount {
    public static void main(String[] args) {

        Address address  = Address.createAddress("Maharashtra", "Mumbai", "c/1123, sao ganesh krupa", 235698);
        System.out.println(address);

        Customer customer = Customer.createCustomer("sweta", 121564, address);
        System.out.println(customer);

        Account account  = Account.createAccount(123456, 3000, customer);
        System.out.println(account);


    }
}

