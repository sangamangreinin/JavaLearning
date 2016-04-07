package com.inin.taskmanagement.domain;

/**
 * Created by Manish Dubey on 5/4/16.
 * Class used for track the event on task
 */
public class TaskEventLog extends BaseDomain{
    /**
     * Event log id
     */
    private long id;
    /**
     * Task id on which events are logged
     */
    private long taskId;
    /**
     * User id, by which event is happening on task
     */
    private long userId;
    /**
     * Event detail
     */
    private String description;

    /**
     * No args constructor
     */
    public TaskEventLog() {
    }

    /**
     * Create the TaskEventLog with task id , user id and details of event
     * @param taskId
     * @param userId
     * @param description
     */
    public TaskEventLog(long taskId, long userId, String description) {
        this.taskId = taskId;
        this.userId = userId;
        this.description = description;
    }

    /**
     * Get the event id
     * @return id of log
     */
    public long getId() {
        return id;
    }

    /**
     * Get the task id on which event is logged
     * @return task id
     */
    public long getTaskId() {
        return taskId;
    }

    /**
     * Get user by which event is happening
     * @return user id
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Get the event description
     * @return description
     */
    public String getDescription() {
        return description;
    }
}
