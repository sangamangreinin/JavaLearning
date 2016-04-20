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
    private int commentId;
    /**
     * comment description
     */
    private String description;
    /**
     * comment done by which user
     */
    private int commentedBy;
    /**
     * Date and time when comment was made on task
     */
    private LocalDateTime commentDateTime;

    public Comment() {
    }

    /**
     * Initialize the comment object
     * @param description
     * @param commentedBy

     */
    public Comment(String description, int commentedBy ) {
        this.description = description;
        this.commentedBy = commentedBy;
        this.commentDateTime = LocalDateTime.now();
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

    public void setId(int commentId) {
        this.commentId = commentId;
    }

    public void setComment(String description) {
        this.description = description;
    }

    public void setCommentedBy(int commentedBy) {
        this.commentedBy = commentedBy;
    }
    public void setCommentDateTime(LocalDateTime commentDateTime) {
        this.commentDateTime = commentDateTime;
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
