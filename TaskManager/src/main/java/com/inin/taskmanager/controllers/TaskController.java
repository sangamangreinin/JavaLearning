package com.inin.taskmanager.controllers;

import com.inin.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    TaskService taskService;


}
