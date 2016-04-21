package com.inin.model;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by root on 6/4/16.
 */

public class Task {

    /**
     * task status in draft
     */
    public static final int DRAFT = 1;
    /**
     * task status in Assigned
     */
    public static final int ASSIGNED = 2;
    /**
     * task status in progress
     */
    public static final int INPROGRESS = 3;
    /**
     * task status complete
     */
    public static final int COMPLETE = 4;

    /**
     * id of task
     */
    private int taskId;
    /**
     * task name
     */
    private String taskName;
    /**
     * description of task
     */
    private String taskDescription;
    /**
     * status of task
     */
    private int taskStatus;
    /**
     * who has assigned the task
     */
    private int assignorId;
    /**
     * to whom the task is assigned
     */
    private int assigneeId;
    /**
     * list of comments
     */
    private List<Comment> comments;
    /**
     * date when task was created
     */
    private LocalDate taskCreatedDate;
    /**
     * date when to start the task
     */
    private LocalDate taskStartDate;

    /**
     * date when to end the task
     */
    private LocalDate dueDate;

    public Task() {
    }

    /**
     * Initialize the task object
     * @param name task name
     * @param taskDescription task description
     * @param taskStatus task status
     * @param assignorId assignor id
     * @param assigneeId assignee id
     * @param taskStartDate start date of task
     * @param dueDate due date of task
     */

    public Task(String name, String taskDescription, int taskStatus, int assignorId, int assigneeId, LocalDate taskStartDate,  LocalDate dueDate) {
        this.taskName           = name;
        this.taskDescription    = taskDescription;
        this.taskStatus         = taskStatus;
        this.assignorId         = assignorId;
        this.assigneeId         = assigneeId;
        this.taskStartDate      = taskStartDate;
        this.dueDate            = dueDate;
    }

    /**
     * get taskId
     * @return the id of task
     */
    public int getTaskId() {
        return taskId;
    }

    /**
     * get task Name
     * @return the task name
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * get task Description
     * @return the task description
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * get task status
     * @return the task status ids
     */
    public int getTaskStatus() {
        return taskStatus;
    }

    /**
     * get assignor of task
     * @return id of the user who assigned the task
     */
    public int getAssignorId() {
        return assignorId;
    }

    /**
     * get task assignedTo
     * @return id of the user to whom the task is assigned
     */
    public int getAssigneeId() {
        return assigneeId;
    }

    /**
     * get comment on task
     * @return list of comments
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * get task start date
     * @return date of when to start task
     */
    public LocalDate getTaskStartDate() {
        return taskStartDate;
    }

    /**
     * get task created date
     * @return the current date
     */
    public LocalDate getTaskCreatedDate() {
        return LocalDate.now();
    }

    /**
     * set task id
     * @param taskId id to set in int
     */
    public void setId(int taskId) {
        this.taskId = taskId;
    }

    /**
     * @param taskName task name to set in string
     */
    public void setName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * set task description
     * @param taskDescription task description to set in string
     */
    public void setDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * set task status
     * @param taskStatus task status to set in int
     */
    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     * set assignor
     * @param assignorId assignor id to set in int
     */
    public void setAssignorId(int assignorId) {
        this.assignorId = assignorId;
    }

    /**
     * set assigned id
     * @param assignedTo assigned to id to set in int
     */
    public void setAssigneeId(int assignedTo) {
        this.assigneeId = assigneeId;
    }

    /**
     * set comment on task
     * @param comments list od comments to set in list
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    /**
     * set task create date
     * @param taskCreatedDate task created date to set in LocalDate
     */
    public void setCreatedDate(LocalDate taskCreatedDate) {
        this.taskCreatedDate = taskCreatedDate;
    }

    /**
     * set task start date
     * @param taskStartDate task start date to set in LocalDate
     */
    public void setStartDate(LocalDate taskStartDate) {
        this.taskStartDate = taskStartDate;
    }

    /**
     *
     * @return due date in LocalDate
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * set task due date
     * @param dueDate task due date to set in LocalDate
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskStatus=" + taskStatus +
                ", assignorId=" + assignorId +
                ", assigneeId=" + assigneeId +
                ", comments=" + comments +
                ", taskCreatedDate=" + taskCreatedDate +
                ", taskStartDate=" + taskStartDate +
                ", dueDate=" + dueDate +
                '}';
    }
}
