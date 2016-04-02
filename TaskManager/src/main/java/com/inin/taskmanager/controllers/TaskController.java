package com.inin.taskmanager.controllers;

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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by virendra on 1/4/16.
 * TaskController class is the entry point for the application
 * This class contains the methods which will serve the caller
 * based on the methods and the URL used by them
 */

@RestController
@RequestMapping(
        path = "/tasks",
        consumes = "application/json",
        produces = "application/json"
    )
public class TaskController {

    /**
     * taskService property
     * instance of TicketService class is injected during initialization
     */
    @Autowired
    TaskService taskService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getTasks(){
        List<Task> tasks = new ArrayList<>();
        HttpStatus status = HttpStatus.OK;
        try{
            tasks = taskService.getTasks();
        } catch (ClassNotFoundException e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } catch (IOException e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(tasks, status);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{taskId}")
    public ResponseEntity getTask(@PathVariable String taskId){

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createTask(@RequestBody Task task){

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT ,path = "/{taskId}")
    public ResponseEntity updateTask(@PathVariable String taskId){

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "{taskId}/comments")
    public ResponseEntity comments(@PathVariable String taskId){

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "{taskId}/comments/{commentId}")
    public ResponseEntity getComment(@PathVariable String taskId,
                                     @PathVariable String commentId){

        return new ResponseEntity(HttpStatus.OK);
    }






}
