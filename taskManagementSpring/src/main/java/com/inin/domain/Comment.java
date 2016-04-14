package com.inin.domain;

import java.time.LocalDateTime;

/**
 * Created by root on 6/4/16.
 */

/**
 *
 */
public class Comment {
    /**
     * id of the comment
     */
    int commentId;
    /**
     * comment description
     */
    String description;
    /**
     * comment done by which user
     */
    User commentedBy;
    /**
     * Date and time when comment was made on task
     */
    LocalDateTime commentDateTime;

    public Comment() {
    }

    public int getCommentId() {
        return commentId;
    }

    public String getDescription() {
        return description;
    }

    public User getCommentedBy() {
        return commentedBy;
    }

    public LocalDateTime getCommentDateTime() {
        return commentDateTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", description='" + description + '\'' +
                ", commentedBy=" + commentedBy +
                ", commentDateTime=" + commentDateTime +
                '}';
    }
}
