package com.inin.taskmanagement.constant;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.List;

/**
 * Created by root on 5/4/16.
 */
public enum TaskStatus {
    DRAFT("draft"),ASSIGNED("assigned"),COMPLETED("completed");

    private String value;
    TaskStatus(final String value) {
        this.value = value;
    }
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Get task status by value
     * @param value
     * @return  Task Status
     */
    public TaskStatus getValue(String value)
    {
        switch (value){
            case "assigned":
                return TaskStatus.ASSIGNED;

            case "completed":
                return TaskStatus.COMPLETED;
            default:
                return TaskStatus.DRAFT;
        }
    }

    /**
     * Validate the status against predefined status
     * @param status
     * @return true if valid status is provided, other wise false
     */
    public static boolean isValidTaskStatus(String status){
        List<String> list = Arrays.asList("draft","assigned","completed");
        return list.contains(status);
    }
}
