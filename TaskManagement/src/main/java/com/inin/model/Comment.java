package com.inin.model;

import java.time.LocalDateTime;

/**
 * Created by root on 5/4/16.
 */
public class Comment {
    private int id;
    private int taskId;
    private String comment;
    private int userId;
    private LocalDateTime created;

    public Comment() {
    }

    public Comment(int taskId, String comment, int userId) {
        this.taskId = taskId;
        this.comment = comment;
        this.userId = userId;
        this.created = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getComment() {
        return comment;
    }

    public int getUserId() {
        return userId;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
