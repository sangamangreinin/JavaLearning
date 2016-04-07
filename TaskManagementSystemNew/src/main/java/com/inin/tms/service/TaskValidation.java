package com.inin.tms.service;


import com.inin.tms.domain.Task;
import com.inin.tms.exception.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Represents validation of task details and set defaults.
 */
@Service
public class TaskValidation {
    /**
     * Validating the required fields for task creation. Also setting the default values for the task
     * @param tasktObject to validate the data
     * @return Task object
     * @throws IllegalArgumentException if required fields are empty
     */
    public Task prepareTask(Task tasktObject) throws BadRequestException {
        if (StringUtils.isEmpty(tasktObject.getTitle())) {
            throw new IllegalArgumentException("Create Task : missing required details of task");
        }
        String status = !StringUtils.isEmpty(tasktObject.getStatus()) ? tasktObject.getStatus() : "Draft";
        tasktObject.setStatus(status);
        return tasktObject;
    }
}
