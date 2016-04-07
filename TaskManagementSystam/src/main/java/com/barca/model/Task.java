package com.barca.model;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by root on 3/4/16.
 */
public class Task {

    private long id;
    private String subject;
    public String getDescription() {
        return description;
    }

    private String description;
    private String status;
    private String commentIds;
    private long assigneeId;
    private long assignerId;
    private LocalDateTime dueDate;
    private LocalDateTime created;
    private LocalDateTime modified;

    public Task(){}

    public long getId() {
        return id;
    }


    public String getSubject() {
        return subject;
    }

    public String getStatus() {
        return status;
    }

    public String getCommentIds() {
        return commentIds;
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
}
