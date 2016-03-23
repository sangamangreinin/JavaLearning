package com.inin.Bank.domain;

/**
 * Created by root on 23/3/16.
 */
public class AdharCard implements KycDocument {
    String adharCardNumber;

    private AdharCard(String adharCardNumber) {
        this.adharCardNumber = adharCardNumber;
    }


    public static AdharCard createAdharCard(String adharCardNumber){
        return new AdharCard(adharCardNumber);
    }
    @Override
    public void uploadDocument() {
        System.out.println("Adhar card uploaded");
    }
}
