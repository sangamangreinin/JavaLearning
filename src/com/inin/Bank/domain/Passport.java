package com.inin.Bank.domain;

/**
 * Created by root on 23/3/16.
 */
public class Passport implements KycDocument {
    String passportNumber;

    private Passport(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public static Passport createPassport(String passportNumber){
        return new Passport(passportNumber);
    }

    @Override
    public void uploadDocument() {
        System.out.println("Passport uploaded");
    }
}
