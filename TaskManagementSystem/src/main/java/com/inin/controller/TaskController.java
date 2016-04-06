package com.inin.controller;

import com.inin.domain.Comment;
import com.inin.domain.Task;
import com.inin.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Deepak on 2/4/16.
 * This is represents controller class for task in system.
 */
@RestController
@RequestMapping(path = "/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * This controller method to create task in system
     * @param task is object passed to store in system.
     * */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Task> create(@RequestBody Task task){
        taskService.createTask(task);
        ResponseEntity<Task> responseEntity = new ResponseEntity<Task>(task, HttpStatus.CREATED);
        return responseEntity;
    }


    /**
     * This is controller get method to get the list of task stroed in system.`
     * */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Task>> get(){

        ResponseEntity<List<Task>> entity = new ResponseEntity<List<Task>>(taskService.getAll(), HttpStatus.OK);
        return entity;
    }


    /**
     * This is controller method to get the particular task on the basis of id provided in url path.
     * */
    @RequestMapping(method = RequestMethod.GET, path = "/{taskId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Task> get(@PathVariable int taskId){

        Task task = taskService.get(taskId);
        if(task == null){
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }


    /**
     * This Controller method to add comments on task on the basis of task id provided in url path.
     * @param comment is comment object to be stored on the task.
     * */
    @RequestMapping(method = RequestMethod.PUT, path = "/{tid}/comments", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Task> addComments(@PathVariable int tid, @RequestBody Comment comment){

        ResponseEntity<Task> responseEntity = new ResponseEntity(taskService.addComment(comment, tid), HttpStatus.ACCEPTED);
        return responseEntity;
    }


    /**
     * This is controller method to update the status of task on the basis of id of task in url path.
     * need user id in headers the one who is going to update the status.
     * @param task updated task object.
     * */
    @RequestMapping(method = RequestMethod.PUT, path = "/{tid}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Task> update(@PathVariable int tid, @RequestBody Task task, @RequestHeader(value = "userId") int userId){

        ResponseEntity<Task> responseEntity = new ResponseEntity(taskService.updateTask(tid, userId, task), HttpStatus.ACCEPTED);
        return responseEntity;
    }
}
