package com.inin.taskmanager.domain;

import com.inin.taskmanager.domain.base.BaseDomain;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by virendra on 1/4/16.
 * Comment class. Represent the comment made on the task
 * Comment is dependent on the Task object
 */

public class Comment extends BaseDomain implements Serializable {

    public static final String TABLE_NAME = "comments";
    private static final long serialVersionUID = 1L;
    /**
     * comment id for the comment
     */
    private Long commentId;

    /**
     * comment content
     */
    private String comment;
    /**
     * user object who makes the comment
     */
    private User commentBy;

    public Comment() {
    }

    public Comment(Builder builder) {
        this.commentBy = builder.commentBy;
        this.commentId = builder.commentId;
        this.comment = builder.comment;
        this.createdDate = builder.createdDate;
        this.modifiedDate = builder.modifiedDate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", comment='" + comment + '\'' +
                ", commentBy=" + commentBy +
                '}';
    }

    /**
     * get comment id
     *
     * @return String comment id
     */
    public Long getCommentId() {
        return commentId;
    }

    /**
     * get comment content
     *
     * @return String content of the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * user object who makes comment
     *
     * @return User object
     */
    public User getCommentBy() {
        return commentBy;
    }

    /**
     * sets the user entry who makes the comment
     */
    public void setCommentBy(User commentBy) {
        this.commentBy = commentBy;
    }

    /**
     * inner class implementing Builder patterns to create Comment object
     */
    public static class Builder {
        private Long commentId;
        private String comment;
        private User commentBy;

        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;

        public Builder(String comment, User commentBy) {
            this.comment = comment;
            this.commentBy = commentBy;
        }

        public Builder setCommentId(Long commentId) {
            this.commentId = commentId;
            return this;
        }

        public Builder setCreatedDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder setModifiedDate(LocalDateTime modifiedDate) {
            this.modifiedDate = modifiedDate;
            return this;
        }

        public Comment create() {
            return new Comment(this);
        }
    }
}
