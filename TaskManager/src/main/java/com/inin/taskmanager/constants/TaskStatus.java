package com.inin.taskmanager.constants;

/**
 * Created by virendra on 1/4/16.
 * TaskStatus Enum
 * Contains the status that a task can have.
 */
public enum TaskStatus {
    /**
     * DRAFT status for the task. This is the default task that will
     * be applied on the task during creation
     */
    DRAFT,
    /**
     * ASSIGNED status of the task. This status will be applied on the
     * task once task has assignee
     */
    ASSIGNED,
    /**
     * POSTPONED status of the task. This task is applied once a creator or
     * assignee postpones the task
     */
    POSTPONED,
    /**
     * COMPLETE status of the task. This status gets applied on the task after
     * the assignee or creator marks the status as complete
     */
    COMPLETE;
}
