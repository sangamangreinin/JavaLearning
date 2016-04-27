package com.inin.model;

import org.springframework.stereotype.Repository;


/**
 * Created by root on 27/4/16.
 */
@Repository
public class Queue {

    private String queueName;
    private String myQueueUrl;

    public Queue() {
    }

    public Queue(String queueName) {
        this.queueName = queueName;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getMyQueueUrl() {
        return myQueueUrl;
    }

    public void setMyQueueUrl(String myQueueUrl) {
        this.myQueueUrl = myQueueUrl;
    }
}
