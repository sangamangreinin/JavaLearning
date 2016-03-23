package com.inin.Bank.Domain;

import java.io.Serializable;

/**
 * Created by evansbelly on 23/3/16.
 */
public class KycInfo implements Serializable {

    private String kycDoc;
    private String kycDocPath;
    private static final int serialVersionUID = 1;

    /**
     * kycInfo object
     *
     * @param kycDoc
     * @param kycDocPath
     */
    public KycInfo(String kycDoc, String kycDocPath) {
        this.kycDoc = kycDoc;
        this.kycDocPath = kycDocPath;
    }

    /**
     * create KycInfo object
     *
     * @param kycDoc
     * @param kycDocPath
     * @return kycInfo
     */
    public static KycInfo createKycInfo(String kycDoc, String kycDocPath) {
        KycInfo kycInfo = new KycInfo(kycDoc, kycDocPath);
        return kycInfo;
    }

    @Override
    public String toString() {
        return "kycDoc = '" + kycDoc + '\'' +
                ", kycDocPath = '" + kycDocPath;
    }
}
