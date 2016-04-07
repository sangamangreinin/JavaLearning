package com.inin.taskmanagement.domain;

import java.time.LocalDateTime;

/**
 * Created by root on 5/4/16.
 */
public class Comment extends BaseDomain{

    private long id;
    private long taskId;
    private long userId;
    private String comment;

    public Comment() {
    }

    public Comment(long id,long taskId, long userId, String comment, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.taskId = taskId;
        this.userId = userId;
        this.comment = comment;
        this.createdDate =  createdDate;
        this.modifiedDate = modifiedDate;
    }

    public long getId() {
        return id;
    }

    public long getTaskId() {
        return taskId;
    }

    public long getUserId() {
        return userId;
    }

    public String getComment() {
        return comment;
    }
}
