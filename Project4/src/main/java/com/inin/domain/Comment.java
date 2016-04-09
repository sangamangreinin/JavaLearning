package com.inin.domain;

import java.time.LocalDateTime;

/** comment model object
 * Created by root on 6/4/16.
 */
public class Comment {

    private int id;

    private String comment;

    private long taskId;

    private int userId;

    private LocalDateTime created;

    /**
     * takes 3 arguments
     * @param comment String comment
     * @param taskId Integer Task Id
     * @param userId Integer user Id
     */
    public Comment(String comment, long taskId, int userId) {
        this.comment = comment;
        this.taskId = taskId;
        this.userId = userId;
    }

    /**
     * sets Task id which is used in TaskDao
     * @param taskId long value
     */
    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    /**
     * sets Created which is used in TaskDao
     * @param created LocalDateTime
     */
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    /**
     * gets comment of the task
     * @return String
     */
    public String getComment() {
        return comment;
    }

    /**
     * gets Task Id of the Task
     * @return integer
     */
    public long getTaskId() {
        return taskId;
    }

    /**
     * gets user Id of the Task
     * @return integer
     */
    public int getUserId() {
        return userId;
    }

    /**
     * gets created date of the Task
     * @return LocalDateTime
     */
    public LocalDateTime getCreated() {
        return created;
    }
}
