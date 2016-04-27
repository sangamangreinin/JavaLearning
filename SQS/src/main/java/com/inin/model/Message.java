package com.inin.model;

/**
 * Created by root on 27/4/16.
 */
public class Message {

    private  String emailId;

    public Message(String emailId) {
        this.emailId = emailId;
    }

    public String getEmailId() {
        return emailId;
    }

    public static Message createMessage(String emailId){
        return new Message(emailId);
    }
}
