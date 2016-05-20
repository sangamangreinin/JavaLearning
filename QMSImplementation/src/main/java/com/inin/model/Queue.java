package com.inin.model;

import java.time.LocalDateTime;

/**
 * Created by root on 12/5/16.
 */
public class Queue {
    /**
     * id of queue
     */
    int id;
    /**
     * name of queue
     */
    String name;
    /**
     * queue producer id
     */
    int producerId;
    /**
     * queue consumer id
     */
    int consumerId;
    /**
     * date and time of queue creation
     */

    LocalDateTime createdDate;

    public Queue() {
    }

    /**
     * initialize the queue object
     * @param name queue name in string
     * @param producerId producer id in int
     * @param consumerId consumer id in int
     */
    public Queue(String name, int producerId, int consumerId) {
        this.name = name;
        this.producerId = producerId;
        this.consumerId = consumerId;
        this.createdDate = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    /**
     * get name of queue
     * @return the name of queue
     */
    public String getName() {
        return name;
    }

    /**
     * set queue name
     * @param name name of queue in string
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the id of producer
     * @return the producer id in int
     */
    public int getProducerId() {
        return producerId;
    }

    /**
     * set the id of producer
     * @param producerId producer id in int
     */
    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    /**
     * get consumer id
     * @return the consumer id in int
     */
    public int getConsumerId() {
        return consumerId;
    }

    /**
     * set the consumer id
     * @param consumerId consume id in int
     */
    public void setConsumerId(int consumerId) {
        this.consumerId = consumerId;
    }

    /**
     * get creation date of queue
     * @return creation date in date and time
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Queue{" +

                "id=" + id +
                ", name='" + name + '\'' +
                ", producerId=" + producerId +
                ", consumerId=" + consumerId +
                ", createdDate=" + createdDate +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if(obj == null || getClass() != obj.getClass() ) return false;

        return  this.getId() == ((Queue) obj).getId();
    }

    @Override
    public int hashCode() {
        return this.getId();
    }
}
