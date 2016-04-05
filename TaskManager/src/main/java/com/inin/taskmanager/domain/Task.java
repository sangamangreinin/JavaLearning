package com.inin.taskmanager.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.LocalDateSerializer;
import com.inin.taskmanager.constants.TaskStatus;
import com.inin.taskmanager.domain.base.BaseDomain;
import com.inin.taskmanager.utils.Util;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

import org.joda.time.LocalDateTime;

import java.util.List;

/**
 * Created by virendra on 1/4/16.
 * Task class. This class represents the Task object in the application
 */
public class Task extends BaseDomain implements Serializable {

    /**
     * table name
     */
    public static final String TABLE_NAME = "tasks";
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
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime endDate;

    public Task() {
    }

    public Task(Builder builder) {
        this.taskId = builder.taskId;
        this.title = builder.title;
        this.description = builder.description;
        this.createdBy = builder.createdBy;
        this.assignedTo = builder.assignedTo;
        this.status = builder.status;
        this.comments = builder.comments;
        this.endDate = builder.endDate;
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
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

    public void setTaskId(String taskId) {
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


    public static class Builder {
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

        @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
        @JsonFormat
        private LocalDateTime createdDate;

        @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
        @JsonFormat
        private LocalDateTime modifiedDate;

        public Builder(String title, String description) {
            this.title = title;
            this.description = description;
        }

        public Builder setModifiedDate(LocalDateTime modifiedDate) {
            this.modifiedDate = modifiedDate;
            return this;
        }

        public Builder setTaskId(String taskId) {
            this.taskId = taskId;
            return this;
        }

        public Builder setCreatedBy(User createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public Builder setAssignedTo(User assignedTo) {
            this.assignedTo = assignedTo;
            return this;
        }

        public Builder setStatus(TaskStatus status) {
            this.status = status;
            return this;
        }

        public Builder setComments(List<Comment> comments) {
            this.comments = comments;
            return this;
        }

        public Builder setEndDate(LocalDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder setCreatedDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }


        public Task create() {
            return new Task(this);
        }
    }
}
