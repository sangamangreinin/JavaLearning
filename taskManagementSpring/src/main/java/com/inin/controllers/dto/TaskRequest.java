package com.inin.controllers.dto;


import java.time.LocalDate;

/**
 * Created by root on 19/4/16.
 */
public class TaskRequest {
    /**
     * task name
     */
    public String name;

    /**
     * description of task
     */
    public String description;

    /**
     * status of task
     */
    public int status;

    /**
     * who has assigned the task
     */
    public int assignee;

    /**
     * to whom the task is assigned
     */
    public int assignedTo;

    /**
     * date when to start the task
     */
    public LocalDate startDate;

    /**
     * date when to end the task
     */
    public LocalDate dueDate;

}
