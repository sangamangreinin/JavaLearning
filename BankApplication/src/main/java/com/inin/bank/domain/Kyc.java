package com.inin.bank.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by root on 23/3/16.
 * Maintaining the KYC document of the customer.
 */
public class Kyc implements Serializable{
    private static final long serialVersionUID = 1L;

    private String id;
    private String kycType;
    private String path;
    private LocalDateTime dateCreated;

    /**
     * creating a new Kyc object
     * @param kycType kyc type contains PAN/aadhar card / passport
     * @param path kyc document path where the actual document stored.
     */
    public Kyc(String kycType, String path) {
        this.id = UUID.randomUUID().toString();
        this.kycType = kycType;
        this.path = path;
        this.dateCreated = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getKycType() {
        return kycType;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "Kyc{" +
                "id='" + id + '\'' +
                ", kycType='" + kycType + '\'' +
                ", path='" + path + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
