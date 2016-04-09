package com.inin.domain;

import java.time.LocalDateTime;

/**
 * Created by evansbelly on 6/4/16.
 */
public class Task {

    private int id;
    private String title = "title";
    private String status;
    private String description;
    private int assigner;
    private int assignedTo;
    private LocalDateTime modified;
    private LocalDateTime created = modified = LocalDateTime.now();
    private LocalDateTime endDate;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public int getAssigner() {
        return assigner;
    }

    public int getAssignedTo() {
        return assignedTo;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Task() {
    }

    public Task(int id, String title, String status, String description, int assigner, int assignedTo, LocalDateTime modified, LocalDateTime created, LocalDateTime endDate) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.description = description;
        this.assigner = assigner;
        this.assignedTo = assignedTo;
        this.modified = modified;
        this.created = created;
        this.endDate = endDate;
    }

    public enum TaskStatus {
        ASSIGNED,
        COMPLETED,
        DRAFT
    }

}
