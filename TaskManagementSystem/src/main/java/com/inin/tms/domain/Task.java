package com.inin.tms.domain;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by root on 2/4/16.
 * Defines the behaviour of the Task class
 */
public class Task {
    /**
     * A unique Task id
     */
    private String id;
    /**
     * Subject of a task
     */
    private String subject;
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
    private List<Comment> comment;
    /**
     * Task created by (Who creates the task)
     */
    private User createdBy;
    /**
     * Who is going to do a task i.e task assigned to a user
     */
    private User assignedTo;
    /**
     * due date of a task
     */
    private LocalDateTime dueDate;
    /**
     * created date of a task
     */
    private LocalDateTime creadted;
    /**
     * Last modified date of a task
     */
    private LocalDateTime modified;


    public String getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public LocalDateTime getCreadted() {
        return creadted;
    }

    public LocalDateTime getModified() {
        return modified;
    }
}
