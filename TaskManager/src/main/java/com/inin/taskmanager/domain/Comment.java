package com.inin.taskmanager.domain;

import com.inin.taskmanager.domain.base.BaseDomain;
import com.inin.taskmanager.utils.Util;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by virendra on 1/4/16.
 * Comment class. Represent the comment made on the task
 * Comment is dependent on the Task object
 */

public class Comment extends BaseDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * comment id for the comment
     */
    private String commentId;

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

    /**
     * get comment id
     *
     * @return String comment id
     */
    public String getCommentId() {
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
     * saves the new Comment entry
     */
    public void save() {
        commentId = Util.getMasterCommentId();
        createdDate = LocalDateTime.now();
        modifiedDate = LocalDateTime.now();
    }

    /**
     * updates details of existing Comment entry
     */
    public void update() {
        modifiedDate = LocalDateTime.now();
    }
}
