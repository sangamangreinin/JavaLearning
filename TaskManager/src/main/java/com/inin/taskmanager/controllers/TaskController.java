package com.inin.taskmanager.controllers;

import com.inin.taskmanager.domain.Comment;
import com.inin.taskmanager.domain.Task;
import com.inin.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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
        produces = "application/json"
    )
public class TaskController {

    /**
     * taskService property
     * instance of TicketService class is injected during initialization
     */
    @Autowired
    TaskService taskService;

    /**
     * gets the taks list
     * @return ResponseEntity
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getTasks(){
        HttpStatus status = HttpStatus.OK;
        ResponseEntity response;
        try{
            List<Task> tasks = taskService.getTasks();
            response = new ResponseEntity(tasks, status);
        } catch (ClassNotFoundException |IOException e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = new ResponseEntity(status);
        }
        return response;
    }

    /**
     * gets the task by the supplied task id
     * @param taskId String task id
     * @return ResponseEntity
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{taskId}")
    public ResponseEntity getTask(@PathVariable String taskId){
        HttpStatus status = HttpStatus.OK;
        ResponseEntity response;
        try{
            Task task = taskService.getTask(taskId);
            if (task == null){
                status = HttpStatus.NOT_FOUND;
                response = new ResponseEntity(status);
            }else
                response = new ResponseEntity(task, status);

        } catch (ClassNotFoundException |IOException e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = new ResponseEntity(status);
        }
        return response;
    }

    /**
     * creates the new task in the application
     * @param task Task Object representation
     * @return ResponseEntity
     */
    @RequestMapping(consumes = "application/json",
            method = RequestMethod.POST)
    public ResponseEntity createTask(@RequestBody Task task){
        HttpStatus status = HttpStatus.OK;
        ResponseEntity response;

        try {
            Task taskObj = taskService.createTask(task);
            response = new ResponseEntity(taskObj, status);
        } catch (IOException e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = new ResponseEntity(status);
        } catch (IllegalAccessException e) {
            status = HttpStatus.BAD_REQUEST;
            response = new ResponseEntity(status);
        }

        return response;
    }

    /**
     * updates the task object
     * @param taskId task object
     * @return Task object
     */
    @RequestMapping(consumes = "application/json",
            method = RequestMethod.PUT ,path = "/{taskId}")
    public ResponseEntity updateTask(@PathVariable String taskId){
        HttpStatus status = HttpStatus.OK;
        ResponseEntity response;

        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * gets the comments made on the task object
     * @param taskId task id
     * @return ResponseEntity
     */
    @RequestMapping(method = RequestMethod.GET, path = "{taskId}/comments")
    public ResponseEntity comments(@PathVariable String taskId){
        HttpStatus status = HttpStatus.OK;
        ResponseEntity response;
        List<Comment> comments;

        try {
            comments = taskService.getComments(taskId);
            if (comments == null){
                status = HttpStatus.NOT_FOUND;
                response = new ResponseEntity(status);
            }else
                response = new ResponseEntity(comments, status);
        } catch (IOException | ClassNotFoundException e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = new ResponseEntity(status);
        }

        return response;
    }

}
