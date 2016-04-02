package com.inin.taskmanager.service;

import com.inin.taskmanager.domain.Comment;
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
     * gets the task list present in the application
     * @return List of task objects
     */
    public List<Task> getTasks() throws IOException, ClassNotFoundException {
        try {
            List<Task> entries = taskRepository.findAll();
            return entries;
        } catch (IOException | ClassNotFoundException e) {
            throw e;
        }
    }

    /**
     * gets the task object
     * @param taskId String task id
     * @return Task object
     */
    public Task getTask(String taskId) throws IOException, ClassNotFoundException {
        return taskRepository.find(taskId);
    }

    /**
     * creates the new task in the application
     * @param task Task object
     * @return Task object
     */
    public Task createTask(Task task) throws IOException {
        return taskRepository.save(task);
    }


    public List<Comment> getComments(String id ) throws IOException, ClassNotFoundException {
        Task task = taskRepository.find(id);
        if (task == null)
            return null;
        return taskRepository.getComments(id);
    }
}
