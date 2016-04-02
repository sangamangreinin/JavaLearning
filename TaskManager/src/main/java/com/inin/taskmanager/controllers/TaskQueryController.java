package com.inin.taskmanager.controllers;

import com.inin.taskmanager.domain.Task;
import com.inin.taskmanager.domain.dao.TaskQueryRequest;
import com.inin.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Created by virendra on 1/4/16.
 * TaskQueryController class is used to search the task object in the application
 * This class contains the methods which will serve the caller
 * based on the methods and the URL used by them
 */

@RestController
@RequestMapping(
        path = "/taskmanager/api",
        produces = "application/json"
)
public class TaskQueryController {

    /**
     * task service instance
     */
    @Autowired
    TaskService taskService;

    /**
     * queries the task
     *
     * @return
     */
    @RequestMapping(
            method = RequestMethod.POST,
            path = "/tasks/query",
            consumes = "application/json"
    )
    public ResponseEntity query(@RequestBody TaskQueryRequest request) {

        HttpStatus status = HttpStatus.OK;
        ResponseEntity response;
        try{
            List<Task> tasks = taskService.query(request);
            response = new ResponseEntity(tasks, status);
        } catch (ClassNotFoundException |IOException e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response = new ResponseEntity(status);
        }
        return response;
    }
}