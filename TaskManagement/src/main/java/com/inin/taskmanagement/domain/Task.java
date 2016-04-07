package com.inin.taskmanagement.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.inin.taskmanagement.constant.TaskStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Manish Dubey on 5/4/16.
 * Class represent the task in the system
 */
public class Task extends BaseDomain{
    /**
     * Task Id
     */
    private long id;
    /**
     * Task Name
     */
    private String name;
    /**
     * Task owner id, user which create the task
     */
    private long taskAssignerId;
    /**
     * Task doer id, which do the task
     */
    private long taskDoerId;
    /**
     * Task status e.g. draft,assigned or completed
     */
    private TaskStatus status;
    /**
     * Task assign date
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate assignDate;
    /**
     * Task due date
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd",iso = DateTimeFormat.ISO.DATE)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dueDate;

    /**
     * No arguments constructor
     */
    public Task() {
    }

    public Task(long id, String name, long taskAssignerId, long taskDoerId, TaskStatus status, LocalDate assignDate, LocalDate dueDate,LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.name = name;
        this.taskAssignerId = taskAssignerId;
        this.taskDoerId = taskDoerId;
        this.status = status;
        this.assignDate = assignDate;
        this.dueDate = dueDate;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    /**
     * Return task Id
     * @return Task Id
     */
    public long getId() {
        return id;
    }

    /**
     * Return the name of task
     * @return name of task
     */
    public String getName() {
        return name;
    }

    /**
     * Return the Id of task creator
     * @return task owner id
     */
    public long getTaskAssignerId() {
        return taskAssignerId;
    }

    /**
     * Return the task doer id
     * @return task doer id
     */
    public long getTaskDoerId() {
        return taskDoerId;
    }

    /**
     * Return current task status
     * @return current status of task
     */
    public TaskStatus getStatus() {
        return status;
    }

    /**
     * Date on which owner assign the task to task doer
     * @return task assign date
     */
    public LocalDate getAssignDate() {
        return assignDate;
    }

    /**
     * Return the task due date on which date task is finish
     * @return task due date
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Set task doer id
     * @param taskDoerId
     */
    public void setTaskDoerId(long taskDoerId) {
        this.taskDoerId = taskDoerId;
        this.modifiedDate = LocalDateTime.now();
    }

    /**
     * Set task status
     * @param status
     */
    public void setStatus(TaskStatus status) {
        this.status = status;
        this.modifiedDate = LocalDateTime.now();
    }

    /**
     * Set task assign date
     * @param assignDate
     */
    public void setAssignDate(LocalDate assignDate) {
        this.assignDate = assignDate;
        this.modifiedDate = LocalDateTime.now();
    }

    /**
     * Set task due date
     * @param dueDate
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        this.modifiedDate = LocalDateTime.now();
    }
}
