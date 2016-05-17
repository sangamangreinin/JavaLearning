package com.example.model;

import java.time.LocalDateTime;

/**
 * Created by root on 12/5/16.
 */
public class Queue {
    int id;
    String name;
    int producerId;
    int consumerId;
    LocalDateTime createdDate;

    public Queue(String name, int producerId, int consumerId) {
        this.name = name;
        this.producerId = producerId;
        this.consumerId = consumerId;
        this.createdDate = createdDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    public int getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(int consumerId) {
        this.consumerId = consumerId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
