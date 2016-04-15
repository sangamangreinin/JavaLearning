package com.inin.domain;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by root on 6/4/16.
 */

public class Task {

    /**
     * task status in progress
     */
    public static final int INPROGRESS = 1;
    /**
     * task status complete
     */
    public static final int COMPLETE = 2;

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
    private int assignee;
    /**
     * to whom the task is assigned
     */
    private int assignedTo;
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



    public Task(int id, String name, String taskDescription, int taskStatus, int assignee, int assignedTo, LocalDate taskStartDate, LocalDate taskCreatedDate) {
        this.taskId             = id;
        this.taskName           = name;
        this.taskDescription    = taskDescription;
        this.taskStatus         = taskStatus;
        this.assignee           = assignee;
        this.assignedTo         = assignedTo;
        this.taskCreatedDate    = taskCreatedDate;
        this.taskStartDate      = taskStartDate;
    }

    public static int getINPROGRESS() {
        return INPROGRESS;
    }

    public static int getCOMPLETE() {
        return COMPLETE;
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
     * get assignee of task
     * @return id of the user who assigned the task
     */
    public int getAssignee() {
        return assignee;
    }

    /**
     * get task assignedTo
     * @return id of the user to whom the task is assigned
     */
    public int getAssignedTo() {
        return assignedTo;
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
     * @return
     */
    public LocalDate getTaskCreatedDate() {
        return LocalDate.now();
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskStatus=" + taskStatus +
                ", assignee=" + assignee +
                ", assignedTo=" + assignedTo +
                ", comments=" + comments +
                ", taskCreatedDate=" + taskCreatedDate +
                ", taskStartDate=" + taskStartDate +
                '}';
    }
}
