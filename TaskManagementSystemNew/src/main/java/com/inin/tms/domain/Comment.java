package com.inin.tms.domain;

import java.time.LocalDateTime;

/**
 * Created by root on 3/4/16.
 * Defines the behaviour of comment class
 */
public class Comment extends BaseDomain{
    private int id;
    private int taskId;
    private String comment;
    private int commentBy;

    public Comment() {
    }

    public Comment(int id, int taskId, String comment, int commentBy, LocalDateTime created) {
        this.id = id;
        this.taskId = taskId;
        this.comment = comment;
        this.commentBy = commentBy;
        this.created = created;
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

    public int getCommentBy() {
        return commentBy;
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
