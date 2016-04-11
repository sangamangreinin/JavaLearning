package com.barca.controllers;

import com.barca.model.Comment;
import com.barca.model.Task;
import com.barca.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by root on 7/4/16.
 */

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * create Task in the system
     *
     * @param userId
     * @param task
     * @return auto generated id by the system
     */
    @RequestMapping(method = RequestMethod.POST, path = "users/{userId}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Callable<Long> createTask(@PathVariable("userId") long userId, @RequestBody Task task) {
        return () -> taskService.createTask(userId, task);
    }


    /**
     * get the task by id
     *
     * @param taskId
     * @return task Object
     */
    @RequestMapping(method = RequestMethod.GET, path = "tasks/{id}")
    @ResponseBody
    public Callable<Task> getTask(@PathVariable("id") long taskId) {
        return () -> taskService.getTask(taskId);
    }


    /**
     * get tasks List by user , status[optional]
     *
     * @param userId
     * @param status
     * @return List Of Task
     */
    @RequestMapping(method = RequestMethod.GET, path = "users/{id}/tasks")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Task> getTasks(@PathVariable("id") long userId, @RequestParam(value = "status", required = false) String status) {
        return taskService.getTasks(userId, status);
    }


    /**
     * update the task based on the provided field in the task object by the caller
     *
     * @param userId
     * @param taskId
     * @param task
     * @return updated task Object
     */
    @RequestMapping(method = RequestMethod.PUT, path = "users/{userId}/tasks/{taskId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Callable<Task> update(@PathVariable("userId") long userId, @PathVariable("taskId") long taskId, @RequestBody Task task) {
        return () -> taskService.updateTask(userId, taskId, task);
    }

    /**
     * add comment to the task
     *
     * @param taskId
     * @param comment
     * @return ResponseEntity HttpStatus.Created
     */
    @RequestMapping(method = RequestMethod.POST, path = "tasks/{id}/comments")
    @ResponseBody
    public Callable<ResponseEntity> createTask(@PathVariable("id") long taskId, @RequestBody Comment comment) {
        return () -> taskService.addComment(taskId, comment);
    }


    /**
     * get all Comments list on the task
     *
     * @param taskId
     * @return List of comment if there else return Empty
     */
    @RequestMapping(method = RequestMethod.GET, path = "tasks/{taskId}/comments")
    @ResponseBody
    public Callable<List<Comment>> getComments(@PathVariable("taskId") long taskId) {
        return () -> taskService.getComments(taskId);
    }

}
