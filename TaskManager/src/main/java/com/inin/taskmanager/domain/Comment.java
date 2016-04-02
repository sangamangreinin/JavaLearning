package com.inin.taskmanager.domain;

import com.inin.taskmanager.domain.base.BaseDomain;

import java.io.Serializable;

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

}
