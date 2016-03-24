package com.inin.bank.domain;

import java.io.Serializable;

/**
 * Created by root on 23/3/16.
 */
public class KycDocument implements Serializable {
    private static final long serialVersionUID = 1;

    /**
     * Kyc document type
     */
    KycDocumentType kycDocumentType;

    /**
     * path of the document
     */
    String path;

    /**
     * create Kyc document
     *
     * @param kycDocumentType KycDocument type
     * @param path            path of the document
     */
    private KycDocument(KycDocumentType kycDocumentType, String path) {
        this.kycDocumentType = kycDocumentType;
        this.path = path;
    }

    /**
     * create kyc document
     *
     * @param kycDocumentType KycDocument type
     * @param path            path of the document
     * @return KycDocument object
     */
    public static KycDocument createKyc(KycDocumentType kycDocumentType, String path) {
        return new KycDocument(kycDocumentType, path);
    }

    /**
     * to display Kycdocument details
     *
     * @return
     */
    @Override
    public String toString() {
        return "KycDocument{" +
                "kycDocumentType=" + kycDocumentType +
                ", path='" + path + '\'' +
                '}';
    }
}
