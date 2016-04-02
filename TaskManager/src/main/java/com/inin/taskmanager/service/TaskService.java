package com.inin.taskmanager.service;

import com.inin.taskmanager.domain.Task;
import com.inin.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by virendra on 1/4/16.
 * TaskService class.
 * Service class which provides the service to the caller for the tasks.
 */
@Service
@Scope("singleton")
public class TaskService {

    /**
     * taskRepository property stores TaskRepository object instance .
     */
    @Autowired
    TaskRepository taskRepository;

    /**
     * default constructor for ticket service
     */
    public TaskService() {

    }

    /**
     *
     * @return
     */
    public List<Task> getTasks() throws IOException, ClassNotFoundException {
        try {
            List<Task> entries = taskRepository.findAll();
            return entries;
        } catch (IOException | ClassNotFoundException e) {
            throw e;
        }
    }
}
