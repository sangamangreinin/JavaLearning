package com.inin.tms.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * Created by root on 2/4/16.
 * Defines the behaviour of the Task class
 */
public class Task extends BaseDomain{
    /**
     * A unique Task id
     */
    private int id;
    /**
     * Subject of a task
     */
    private String title;
    /**
     * Description of a task
     */
    private String description;
    /**
     * Status of a task.
     */
    private String status;
    /**
     * comments on a task.
     */
    private int createdBy;
    /**
     * Who is going to do a task i.e task assigned to a user
     */
    private int assignedTo;
    /**
     * due date of a task
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime dueDate;

    /**
     * Assigned date of a task
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime assignedDate;

    /**
     * No argument constructor
     */
    public Task() {
    }

    public Task(int id, String title, String description, String status, int createdBy, int assignedTo, LocalDateTime dueDate, LocalDateTime assignedDate,
                LocalDateTime created, LocalDateTime modified) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdBy = createdBy;
        this.assignedTo = assignedTo;
        this.dueDate = dueDate;
        this.assignedDate = assignedDate;
        this.created = created;
        this.modified = modified;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public int getAssignedTo() {
        return assignedTo;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public LocalDateTime getAssignedDate() {
        return assignedDate;
    }

    public void setStatus(String status) {
        this.status = status;
        this.modified = LocalDateTime.now();
    }

    public void setAssignedTo(int assignedTo) {
        this.assignedTo = assignedTo;
        this.modified = LocalDateTime.now();
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
        this.modified = LocalDateTime.now();
    }

    public void setAssignedDate(LocalDateTime assignedDate) {
        this.assignedDate = assignedDate;
        this.modified = LocalDateTime.now();
    }
    /**
     * To set the modified date
     */
    public void update() {
        this.modified = LocalDateTime.now();
    }

}
