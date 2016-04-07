package com.inin.tms.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by root on 2/4/16.
 * Defines the behaviour of the Task class
 */
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

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
    private List<Comment> comments;
    /**
     * Task created by (Who creates the task)
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
    private LocalDate dueDate;
    /**
     * created date of a task
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate creadted;
    /**
     * Last modified date of a task
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate modified;

    public Task() {
    }

    public Task(int id, String title, String description, String status, int createdBy, int assignedTo) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
       // this.comments = comments;
        this.createdBy = createdBy;
        this.assignedTo = assignedTo;
        //this.dueDate = dueDate;
       // this.creadted = creadted;
       // this.modified = modified;
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

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public int getAssignedTo() {
        return assignedTo;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * To set the unique id, created date & modified date
     */
    public void save() {
        this.creadted = LocalDate.now();
        this.modified = LocalDate.now();
    }

    /**
     * To set the modified date
     */
    public void update() {
        this.modified = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", comment=" + comments +
                ", createdBy=" + createdBy +
                ", assignedTo=" + assignedTo +
                ", dueDate=" + dueDate +
                ", creadted=" + creadted +
                ", modified=" + modified +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }
}
