package com.inin.controller.dto;

import com.inin.helper.Status;

/**
 * Created by root on 5/4/16.
 */
public class TaskRequest {
    public String title;
    public String description;
    public Status status;
    public int assigner;
    public int assignee;
    public String startTime;
    public String dueTime;
}
