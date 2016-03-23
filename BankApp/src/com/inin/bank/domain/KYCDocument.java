package com.inin.bank.domain;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by lokesh on 23/3/16.
 * Representing a KYCDocument of Customer
 */
public class KYCDocument implements Serializable{
    private static final int serialVersionUID = 1;

    private long kycId;
    private Enum<KYCType> kycType;
    private String documentPath;

    /**
     * Constructor to create KYC Document
     * @param kycType Type of Document
     * @param documentPath Path of Document on File System
     */
    public KYCDocument(Enum<KYCType> kycType,String documentPath) {
        this.kycId = Integer.toUnsignedLong(UUID.randomUUID().hashCode());
        this.kycType = kycType;
        this.documentPath = documentPath;
    }

    /**
     * Copy Constructor
     * @param kycDocument kycDocument object by which new Object to be created
     */
    public KYCDocument(KYCDocument kycDocument) {
        this.kycId = kycDocument.kycId;
        this.kycType = kycDocument.kycType;
        this.documentPath = kycDocument.documentPath;
    }

    /**
     * String of KYC Document
     * @return String od KYC Document
     */
    public String toString() {
        return " KycId:" + kycId + " kycType:" + kycType + " Document:" + documentPath;
    }
}
