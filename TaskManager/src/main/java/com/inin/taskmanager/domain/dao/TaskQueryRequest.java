package com.inin.taskmanager.domain.dao;

import com.inin.taskmanager.constants.TaskStatus;

/**
 * Created by virendra on 2/4/16.
 * Query Request class. This stores the search request to search tasks
 *
 */

public class TaskQueryRequest {

    /**
     * status property to search tasks by parameter
     */
    private TaskStatus status;

    public TaskStatus getStatus() {
        return status;
    }
}
