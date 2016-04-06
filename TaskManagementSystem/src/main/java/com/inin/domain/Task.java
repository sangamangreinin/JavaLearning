package com.inin.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deepak on 2/4/16.
 * This is domain model class of system, represents the Task created in system by user and assign to user.
 */
public class Task {
    /** This is unique id of Task in system.*/
    private int id;

    /** This is system user who create the task in system.*/
    private SystemUser assigner;

    /** This is system user to whome the task is assigned. */
    private SystemUser assignee;

    /** This is title of main heading of task created in system. */
    private String title;

    /** This is description of task to be execute*/
    private String description;

    /** This is current status of task */
    private Status currentStatus;

    /** This is tame and date of when the task is created in system. */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime created;

    /** This is assign date of task when the task is assign to particular user in system. */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime assingDate;

    /** This is due date of task when the task is supposed to be completed. */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dueDate;

    /** This is the list of comments made on task by Assigner of Assignee. */
    private List<Comment> comments = new ArrayList<>();

    /** This is List of task events happened on task in system. like assign or comment or mark complete. */
    private List<TaskEvent> taskEvents = new ArrayList<>();


    public Task(){
        this.currentStatus = Status.CREATED;
        this.created = LocalDateTime.now();
    }


    public int getId() {
        return id;
    }

    public SystemUser getAssigner() {
        return assigner;
    }

    public SystemUser getAssignee() {
        return assignee;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Status getCurrentStatus() {
        return currentStatus;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public LocalDateTime getAssingDate() {
        return assingDate;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<TaskEvent> getTaskEvents() {
        return taskEvents;
    }

    public void setCurrentStatus(Status currentStatus) {
        this.currentStatus = currentStatus;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public void setAssingDate(LocalDateTime assingDate) {
        this.assingDate = assingDate;
    }

    public void setAssignee(SystemUser assignee) {
        this.assignee = assignee;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task{" +
                ", assigner=" + assigner +
                ", assignee=" + assignee +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", currentStatus=" + currentStatus +
                ", created=" + created +
                ", dueDate=" + dueDate +
                ", assingDate=" + assingDate +
                ", comments=" + comments +
                ", taskEvents=" + taskEvents +
                '}';
    }
}
