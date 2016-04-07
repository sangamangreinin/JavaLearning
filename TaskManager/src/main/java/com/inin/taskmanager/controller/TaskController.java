package com.inin.taskmanager.controller;

import com.inin.taskmanager.controller.dto.TaskQueryRequest;
import com.inin.taskmanager.domain.Comment;
import com.inin.taskmanager.domain.Task;
import com.inin.taskmanager.exception.RecordNotCreatedException;
import com.inin.taskmanager.exception.RecordNotFoundException;
import com.inin.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by virendra on 1/4/16.
 * TaskController class is the entry point for the application
 * This class contains the methods which will serve the caller
 * based on the methods and the URL used by them
 */

@RestController
@RequestMapping(
        path = "/taskmanager/api/tasks",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
public class TaskController {

    /**
     * taskService property
     * instance of TicketService class is injected during initialization
     */
    @Autowired
    TaskService taskService;


    /**
     * creates the new task in the application and returns the
     * task id dto the caller with 201 STATUS code
     * @param task Task Object representation
     * @return ResponseEntity
     */
    @RequestMapping(
            consumes = "application/json",
            method = RequestMethod.POST
    )
    public ResponseEntity createTask(@RequestBody Task task)
            throws IllegalAccessException
    {
        Long taskId = taskService.createTask(task);
        if(taskId != 0)
            return new ResponseEntity(taskId, HttpStatus.CREATED);
        else
            throw new RecordNotCreatedException("Oops!! something went wrong");
    }

    /**
     * gets the task by the supplied task id
     * @param taskId String task id
     * @return ResponseEntity
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{taskId}")
    public ResponseEntity getTask(@PathVariable String taskId) {

        Task task = taskService.getTask(taskId);

        if (task == null)
            throw new RecordNotFoundException("Task with id "+ taskId+" cannot be found");


        return new ResponseEntity(task, HttpStatus.OK);
    }

    /**
     * gets the task list
     * @return ResponseEntity
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getTasks(
            @RequestParam(name = "user",required = false) String user
            ){

        if (user!= null)
            return new ResponseEntity(taskService.getTasksForUser(user), HttpStatus.OK);

        return new ResponseEntity(taskService.getTasks(), HttpStatus.OK);
    }

    /**
     * gets the comments made on the task object
     * @param taskId task id
     * @return ResponseEntity
     */
    @RequestMapping(method = RequestMethod.GET, path = "{taskId}/comments")
    public ResponseEntity comments(@PathVariable Long taskId){

        TaskQueryRequest r = new TaskQueryRequest.Builder().withComment().withTaskId(taskId).build();
        List<Comment> comments =
        taskService.searchComments(r);
        return new ResponseEntity(comments, HttpStatus.OK);
    }

    /**
     * adds new comment to the mentioned task id by the caller
     * @param taskId long task id
     * @param comment Comment object
     * @return ResponseEntity object
     * @throws IllegalAccessException if comment object is not supplied properly
     */
    @RequestMapping(method = RequestMethod.POST, path = "{taskId}/comments")
    public ResponseEntity addComment(@PathVariable Long taskId, @RequestBody Comment comment)
            throws IllegalAccessException {
        Long commentId = taskService.addComment(taskId, comment);
        if(commentId != 0)
            return new ResponseEntity(commentId, HttpStatus.CREATED);
        else
            throw new RecordNotCreatedException("Oops!! something went wrong");

    }

    /**
     * updates the task object
     * @param taskId task object
     * @return Task object
     *//*
    @RequestMapping(consumes = "application/json",
            method = RequestMethod.PUT ,path = "/{taskId}")
    public ResponseEntity updateTask(@PathVariable String taskId){
        HttpStatus status = HttpStatus.OK;
        ResponseEntity response;

        return new ResponseEntity(HttpStatus.OK);
    }

    */

}
