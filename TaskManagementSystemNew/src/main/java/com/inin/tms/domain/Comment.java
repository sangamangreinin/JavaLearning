package com.inin.tms.domain;

import java.time.LocalDateTime;

/**
 * Created by root on 3/4/16.
 *
 */
public class Comment {
    private int id;
    private int taskId;
    private String comment;
    private int commentBy;
    private LocalDateTime created;

    public Comment(int id, int taskId, String comment, int commentBy) {
        this.id = id;
        this.taskId = taskId;
        this.comment = comment;
        this.commentBy = commentBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getCommentBy() {
        return commentBy;
    }

    public void setCommentBy(int commentBy) {
        this.commentBy = commentBy;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void save(){
        this.created = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", comment='" + comment + '\'' +
                ", commentBy=" + commentBy +
                ", created=" + created +
                '}';
    }
}
