package com.barca.controllers;

import com.barca.model.Comment;
import com.barca.model.Task;
import com.barca.model.User;
import com.barca.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;

/**
 * Created by root on 7/4/16.
 */

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;


    @RequestMapping(method = RequestMethod.POST, path = "users/{id}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Callable<Long> createTask(@PathVariable("id") long id, @RequestBody Task task) {
        return () -> taskService.createTask(id, task);
    }

    @RequestMapping(method = RequestMethod.GET, path = "tasks/{id}")
    @ResponseBody
    public Callable<Task> getTask(@PathVariable("id") long id) {
        return () -> taskService.getTask(id);
    }


    @RequestMapping(method = RequestMethod.POST, path = "tasks/{taskId}/comments ")
    @ResponseBody
    public Callable<ResponseEntity> createTask(@PathVariable("taskId") long taskId, @RequestBody Comment comment) {
        return () -> taskService.addComment(taskId, comment);
    }
}
