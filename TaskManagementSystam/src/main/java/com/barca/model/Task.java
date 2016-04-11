package com.barca.model;

import java.time.LocalDateTime;

/**
 * Created by root on 3/4/16.
 */
public class Task {

    // Task Id
    private long id;

    //task Subject
    private String subject;
    // task Description
    private String description;

    // task status
    private String status;

    //task Assignee for the task
    private long assigneeId;

    //task Assigner for the task
    private long assignerId;

    // Due date for the Task
    private LocalDateTime dueDate;

    // created date for the Task
    private LocalDateTime created;

    // modified date for the Task
    private LocalDateTime modified;

    public Task() {
    }

    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }


    public String getDescription() {
        return description;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAssigneeId(long assigneeId) {
        this.assigneeId = assigneeId;
    }

    public void setAssignerId(long assignerId) {
        this.assignerId = assignerId;
    }

    public String getSubject() {
        return subject;
    }

    public String getStatus() {
        return status;
    }


    public long getAssigneeId() {
        return assigneeId;
    }

    public long getAssignerId() {
        return assignerId;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getModified() {
        return modified;
    }


    /**
     * @param id
     * @param subject
     * @param description
     * @param status
     * @param assigneeId
     * @param assignerId
     * @param dueDate
     * @param created
     * @param modified
     */
    public Task(long id, String subject, String description, String status, long assigneeId, long assignerId, LocalDateTime dueDate, LocalDateTime created, LocalDateTime modified) {
        this.id = id;
        this.subject = subject;
        this.description = description;
        this.status = status;
        this.assigneeId = assigneeId;
        this.assignerId = assignerId;
        this.dueDate = dueDate;
        this.created = created;
        this.modified = modified;
    }

    /**
     * @param id
     * @param subject
     * @param description
     * @param status
     * @param assigneeId
     * @param assignerId
     */
    public Task(long id, String subject, String description, String status, long assigneeId, long assignerId) {
        this.id = id;
        this.subject = subject;
        this.description = description;
        this.status = status;
        this.assigneeId = assigneeId;
        this.assignerId = assignerId;

    }
}
