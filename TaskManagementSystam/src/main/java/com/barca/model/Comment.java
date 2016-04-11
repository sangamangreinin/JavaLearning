package com.barca.model;

import java.time.LocalDateTime;

/**
 * Created by root on 3/4/16.
 */

public class Comment {
    //Task Comment for the task
    private String comment;
    // created date for the task
    private LocalDateTime created;
    //comment by userId
    private long userId;
    //taskId for this is comment
    private long taskId;

    public long getTaskId() {
        return taskId;
    }

    /**
     * @param comment
     * @param created
     * @param userId
     * @param taskId
     */
    public Comment(String comment, LocalDateTime created, long userId, long taskId) {
        this.comment = comment;
        this.created = created;
        this.userId = userId;
        this.taskId = taskId;
    }

    public Comment() {
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public long getUserId() {
        return userId;
    }

}
