package com.inin.tms.service;

import com.inin.tms.domain.Task;
import com.inin.tms.repositary.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by root on 2/4/16.
 * Defines the behaviour of the TaskService class
 */
@Service
public class TaskService extends BaseService {

    @Autowired
    private TaskRepository taskRepository;

    /**
     * Creating a new task
     * @param task Task object
     * @return Task object
     */
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Get atask
     * @param id Task id which is used to get the task details
     * @return Task object
     */
    public Task getTask(String id){
        return taskRepository.find(id);
    }

    /**
     *  Get all tasks
     * @return All tasks
     */
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }
}
