package com.inin.model;

import java.time.LocalDateTime;

/**
 * Created by root on 5/4/16.
 */
public class Task {
    private int id;
    private String title;
    private String description;
    private String status;
    private int assigner;
    private int assignee;
    private LocalDateTime startTime;
    private LocalDateTime dueTime;
    private LocalDateTime created;
    private LocalDateTime modified;

    public Task() {

    }

    public Task(String title, String description, String status, int assigner, int assignee, LocalDateTime startTime, LocalDateTime endTime) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.assigner = assigner;
        this.assignee = assignee;
        this.startTime = startTime;
        this.dueTime = endTime;
        this.created = this.modified = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAssigner() {
        return assigner;
    }

    public void setAssigner(int assigner) {
        this.assigner = assigner;
    }

    public int getAssignee() {
        return assignee;
    }

    public void setAssignee(int assignee) {
        this.assignee = assignee;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getDueTime() {
        return dueTime;
    }

    public void setDueTime(LocalDateTime dueTime) {
        this.dueTime = dueTime;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}
