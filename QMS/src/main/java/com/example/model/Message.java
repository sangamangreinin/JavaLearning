package com.example.model;

import java.time.LocalDateTime;

/**
 * Created by root on 13/5/16.
 */
public class Message {

    int id;
    String message;
    LocalDateTime createdDateTime;
    boolean isProcessed;
    int queueId;

    public Message(String message, boolean isProcessed, int queueId) {
        this.message = message;
        this.isProcessed = isProcessed;
        this.queueId = queueId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }

    public int getQueueId() {
        return queueId;
    }

    public void setQueueId(int queueId) {
        this.queueId = queueId;
    }
}
