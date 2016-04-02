package com.inin.taskmanager.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.inin.taskmanager.constants.TaskStatus;
import com.inin.taskmanager.domain.base.BaseDomain;
import com.inin.taskmanager.utils.Util;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by virendra on 1/4/16.
 * Task class. This class represents the Task object in the application
 */
public class Task extends BaseDomain implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * unique task id for the task
     */
    private String taskId;
    /**
     * title of the task
     */
    private String title;
    /**
     * description of the task
     */
    private String description;
    /**
     * createdBy stores the user object who creates the task
     */
    private User createdBy;

    /**
     * assignedTo store the reference to User object to whom task si assigned
     */
    private User assignedTo;
    /**
     * status of the task
     */
    private TaskStatus status;
    /**
     * comments made on the task
     */
    private List<Comment> comments;
    /**
     * end date of the task
     */
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    @JsonFormat
    private LocalDateTime endDate;

    public Task() {
    }

    /**
     * gets the user object whom the task is assigned
     *
     * @return User object
     */
    public User getAssignedTo() {
        return assignedTo;
    }

    /**
     * gets the current status of the task
     *
     * @return TaskStatus object
     */
    public TaskStatus getStatus() {
        return status;
    }

    /**
     * gets the list of comment made on the task
     *
     * @return List of comment objects
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * gets the end date for the task
     *
     * @return DateTime object
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * gets the task id for the task
     *
     * @return String unique task id
     */
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId){
        this.taskId = taskId;
    }

    /**
     * gets the title of the task
     *
     * @return Sgtring title of the task
     */
    public String getTitle() {
        return title;
    }

    /**
     * gets the description of the task
     *
     * @return String description
     */
    public String getDescription() {
        return description;
    }

    /**
     * gets the creator of the task
     *
     * @return User object
     */
    public User getCreatedBy() {
        return createdBy;
    }

    /**
     * saves the task in the application
     */
    public void save() {
        taskId = Util.getMasterTaskId();
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();

    }

    /**
     * updates the existing task object
     */
    public void update() {
        this.modifiedDate = LocalDateTime.now();
    }
}
