package com.inin.domain;

/**
 * Created by Deepak on 2/4/16.
 * This is predefined status of Task.
 * initially task status is CREATED,
 * after assigning to any user it will be ASSIGNED,
 * after verify and mark as complete it will be COMPLETED.
 */
public enum Status {
    CREATED,
    ASSIGNED,
    COMPLETED
}
