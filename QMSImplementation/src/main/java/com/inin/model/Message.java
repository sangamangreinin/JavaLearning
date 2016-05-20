package com.inin.model;

import java.time.LocalDateTime;

/**
 * Created by root on 13/5/16.
 */
public class Message {

    /**
     * id of message
     */
    int id;
    /**
     * message to be stored in queue
     */
    String message;
    /**
     * message crated date and ttime
     */
    LocalDateTime createdDateTime;
    /**
     * message is consumed
     */
    boolean isProcessed;
    /**
     * queue where message is stored
     */
    int queueId;

    /**
     * date and time when the message was processed
     */
    LocalDateTime consumedDateTime;

    public Message() {
    }

    /**
     * Initialize the message object
     * @param message message in string
     * @param isProcessed message is processed in int
     * @param queueId id of queue in int
     */
    public Message(String message, boolean isProcessed, int queueId) {
        this.message = message;
        this.isProcessed = isProcessed;
        this.queueId = queueId;
        this.createdDateTime  = LocalDateTime.now();
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    /**

     * get message id
     * @return message id int
     */
    public int getId() {
        return id;
    }

    /**
     * set message id
     * @param id message id in int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * get message
     * @return the message in string
     */
    public String getMessage() {
        return message;
    }

    /**
     * set message
     * @param message message in string
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * message created date and time
     * @return message created date
     */
    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    /**
     * message is prcessed or not
     * @return mesage processed in boolean
     */
    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }

    /**
     * get queue id
     * @return queue id in int
     */
    public int getQueueId() {
        return queueId;
    }

    /**
     * set queue id
     * @param queueId queue id in int
     */
    public void setQueueId(int queueId) {
        this.queueId = queueId;
    }

    /**
     * get the consumed date and time
     * @return localdatetime when the message was consumed
     */
    public LocalDateTime getConsumedDateTime() {
        return consumedDateTime;
    }

    /**
     * set the message consumed date and time
     * @param consumedDateTime message consumed date and time
     */
    public void setConsumedDateTime(LocalDateTime consumedDateTime) {
        this.consumedDateTime = consumedDateTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", createdDateTime=" + createdDateTime +
                ", isProcessed=" + isProcessed +
                ", queueId=" + queueId +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if(obj == null || getClass() != obj.getClass() ) return false;

        return  this.getId() == ((Message) obj).getId();
    }

    @Override
    public int hashCode() {
        return this.getId();
    }
}
