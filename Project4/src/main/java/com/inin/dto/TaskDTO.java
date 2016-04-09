package com.inin.dto;

/** Request DTO object used by the Client to make API calls
 * Created by root on 6/4/16.
 */
public class TaskDTO {

    private String description;

    private String status;

    private int assignee;

    private int assigned_to;

    private String dueDate;

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public int getAssignee() {
        return assignee;
    }

    public int getAssigned_to() {
        return assigned_to;
    }

    public String getDueDate() {
        return dueDate;
    }
}
