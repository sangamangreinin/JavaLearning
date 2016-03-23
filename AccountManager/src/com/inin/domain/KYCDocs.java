package com.inin.domain;

import com.inin.constants.KYCConstants;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by virendradubey on 23/3/16.
 */
public class KYCDocs implements Serializable{


    private static final long serialVersionUID = 1;
    private String kycID;
    private KYCConstants kycType;
    private LocalDateTime dateCreated;

    public KYCDocs(KYCDocsBuilder kycDocsBuilder) {
        this.kycID = kycDocsBuilder.kycID;
        this.kycType = kycDocsBuilder.kycType;
        this.dateCreated = LocalDateTime.now();
    }

    public String getKycID() {
        return kycID;
    }


    public KYCConstants getKycType() {
        return kycType;
    }


    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public static class KYCDocsBuilder {

        public String kycID;
        public KYCConstants kycType;

        public KYCDocsBuilder forType(KYCConstants kycType){
            this.kycType = kycType;
            return this;
        }
        public KYCDocsBuilder withUID(String kycID) {
            this.kycID = kycID;
            return this;
        }
        
        public KYCDocs create(){
            return new KYCDocs(this);
        }

    }
}
