package com.inin.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by root on 23/3/16.
 */
public class KYCDocument implements Serializable{
    private static final long serialVersionUID = 1L;
    private String  id;
    private String kycDocumentId;
    private String type;
    private String path;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;

    public KYCDocument(String kycDocumentId, String type, String path) {
        this.id = UUID.randomUUID().toString();
        this.kycDocumentId = kycDocumentId;
        this.type = type;
        this.path = path;
        this.dateCreated = this.dateModified = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getKycDocumentId() {
        return kycDocumentId;
    }

    public String getType() {
        return type;
    }

    public String getPath() {
        return path;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KYCDocument that = (KYCDocument) o;

        if (!kycDocumentId.equals(that.kycDocumentId)) return false;
        return type.equals(that.type);

    }

    @Override
    public int hashCode() {
        int result = kycDocumentId.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
