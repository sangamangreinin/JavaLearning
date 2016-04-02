package com.inin.tms.controller;

import com.inin.tms.domain.Task;
import com.inin.tms.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by root on 2/4/16.
 * This TaskController class interacts between user & service class.
 */
@RestController
@RequestMapping(path = "/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * Creating a new task
     * @param task Task object with all task data
     * @return Task object in json format
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity createTask(@RequestBody Task task) {
        Task newTask = taskService.createTask(task);
        ResponseEntity responseEntity =  new ResponseEntity(newTask, HttpStatus.CREATED);
        return responseEntity;
    }

    /**
     * Get a task details
     * @param id Task id to get a task details
     * @return Task details in the form of json
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{taskId}", produces = "application/json")
    public ResponseEntity getTask(@PathVariable String id){
        Task task = taskService.getTask(id);
        if(task == null)
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(task, HttpStatus.OK);
        }
    }

    /**
     * Get all tasks
     * @return all Tasks
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getTasks(){
        List<Task> tasks = taskService.getTasks();
        ResponseEntity<List<Task>> responseEntity = new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
        return responseEntity;
    }
}
