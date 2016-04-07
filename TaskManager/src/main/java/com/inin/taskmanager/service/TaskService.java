package com.inin.taskmanager.service;

import com.inin.taskmanager.controller.dto.TaskQueryRequest;
import com.inin.taskmanager.domain.Comment;
import com.inin.taskmanager.domain.Task;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by virendra on 1/4/16.
 * TaskService class.
 * Service class which provides the service to the caller for the tasks.
 */
@Service
public interface TaskService {

    Long createTask(Task task) throws IllegalAccessException;

    Task getTask(String taskId);

    List<Task> getTasks();

    List<Task> getTasksForUser(String userId);

    List<Comment> searchComments(TaskQueryRequest request);

    Long addComment(Long taskId, Comment comment) throws IllegalAccessException;
}
