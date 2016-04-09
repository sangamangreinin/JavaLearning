package com.inin.domain;

import java.time.LocalDateTime;

/** Task model object  extending CreateModify super class
 * Created by root on 6/4/16.
 */
public class Task extends CreateModify{

    private int id;

    private String description;

    private String status;

    private int assignee;

    private int assignedTo;

    private LocalDateTime dueDate;

    /**
     * takes 5 parameters
     * @param description - String description
     * @param status String Status
     * @param assignee integer user Id
     * @param assigned_to integer user Id
     * @param dueDate LocalDateTime value
     */
    public Task(String description, String status, int assignee, int assigned_to, LocalDateTime dueDate) {
        this.description = description;
        this.status = status;
        this.assignee = assignee;
        this.assignedTo = assigned_to;
        this.dueDate = dueDate;
    }

    /**
     * Sets Task Id
     * @param id integer Id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * sets new Status
     * @param status - String value of Status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * sets LocalDateTime value
     * @param dueDate LocalDateTime
     */
    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * sets created date for the task / Not needed but it was necessary in TaskDAOImpl
     * @param created LocalDateTime
     */
    public void setCreated(LocalDateTime created) {
        super.created = created;
    }

    /**
     * sets modified date for the task / Not needed but it was necessary in TaskDAOImpl
     * @param modified - LocalDateTime
     */
    public void setModified(LocalDateTime modified) {
        super.modified = modified;
    }

    /**
     * gets Task id
     * @return integer
     */
    public int getId() {
        return id;
    }

    /**
     * gets Task description
     * @return string
     */
    public String getDescription() {
        return description;
    }

    /**
     * gets Task Status
     * @return string
     */
    public String getStatus() {
        return status;
    }

    /**
     * gets task assigner of that task
     * @return integer
     */
    public int getAssignee() {
        return assignee;
    }

    /**
     * gets task assigned to user of that task
     * @return integer
     */
    public int getAssignedTo() {
        return assignedTo;
    }

    /**
     * gets task due date
     * @return LocalDateTime
     */
    public LocalDateTime getDueDate() {
        return dueDate;
    }

}
