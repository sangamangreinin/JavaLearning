package com.inin.dao;

import java.time.LocalDateTime;

/**
 * Created by root on 14/4/16.
 */
public class CommentDao {
    private int id;
    private String comment;
    private int commentedBy;
    private LocalDateTime commentDateTime;
    private int taskId;

    public CommentDao(int id, String comment, int commentedBy, LocalDateTime commentDateTime, int taskId) {
        this.id = id;
        this.comment = comment;
        this.commentedBy = commentedBy;
        this.commentDateTime = commentDateTime;
        this.taskId = taskId;
    }

    public int getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public int getCommentedBy() {
        return commentedBy;
    }

    public LocalDateTime getCommentDateTime() {
        return commentDateTime;
    }

    public int getTaskId() {
        return taskId;
    }

    @Override
    public String toString() {
        return "CommentDao{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", commentedBy=" + commentedBy +
                ", commentDateTime=" + commentDateTime +
                ", taskId=" + taskId +
                '}';
    }
}
