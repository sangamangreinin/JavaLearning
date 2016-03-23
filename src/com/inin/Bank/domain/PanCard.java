package com.inin.Bank.domain;

/**
 * Created by root on 23/3/16.
 */
public class PanCard implements KycDocument {
    String panCardNumber;

    private PanCard(String panCardNumber) {
        this.panCardNumber = panCardNumber;
    }

    public static PanCard createPanCard(String panCardNumber){
        return new PanCard(panCardNumber);
    }
    @Override
    public void uploadDocument() {
        System.out.println("PAN card uploaded");
    }
}
