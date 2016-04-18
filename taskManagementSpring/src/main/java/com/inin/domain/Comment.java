package com.inin.domain;

import java.time.LocalDate;
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
    int commentedBy;
    /**
     * Date and time when comment was made on task
     */
    LocalDateTime commentDateTime;

    public Comment() {
    }

    /**
     * Initialize the comment object
     * @param commentId
     * @param description
     * @param commentedBy
     * @param commentDateTime
     */
    public Comment(int commentId, String description, int commentedBy, LocalDateTime commentDateTime ) {
        this.commentId = commentId;
        this.description = description;
        this.commentedBy = commentedBy;
        this.commentDateTime = commentDateTime;
    }

    public int getCommentId() {
        return commentId;
    }

    /**
     * get the comments
     * @return the string of comments
     */
    public String getDescription() {
        return description;
    }

    /**
     * user who commented
     * @return the user id
     */
    public int getCommentedBy() {
        return commentedBy;
    }

    public LocalDate getCommentDateTime() {
        return LocalDate.now();
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
