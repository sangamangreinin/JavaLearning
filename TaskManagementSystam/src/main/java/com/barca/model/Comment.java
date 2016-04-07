package com.barca.model;

import java.time.LocalDateTime;

/**
 * Created by root on 3/4/16.
 */

public class Comment {
    private String comment;
    private LocalDateTime created;
    private long userId;
    private long taskId;

    public long getTaskId() {
        return taskId;
    }

    public Comment(){}

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
