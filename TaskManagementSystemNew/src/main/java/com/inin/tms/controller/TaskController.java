package com.inin.tms.controller;

import com.inin.tms.domain.Comment;
import com.inin.tms.domain.Task;
import com.inin.tms.exception.BadRequestException;
import com.inin.tms.exception.ResourceNotFoundException;
import com.inin.tms.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by root on 2/4/16.
 * This TaskController class interacts between user & service class.
 */
@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * Creating a new task
     * @param task Task object with all task data
     * @return Task object in json format
     * @throws IllegalArgumentException Task data is invalid.
     */
    @RequestMapping(method = RequestMethod.POST, path = "/users/{userId}/tasks" ,
                produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity create(@RequestBody final Task task, @PathVariable String userId) {
         try {
            int id = Integer.parseInt(userId);
            int taskId = taskService.create(task, id);
            return new ResponseEntity("Task " + taskId + " is created successfully!", HttpStatus.CREATED);
         }
        catch (IllegalArgumentException e) {
            return  new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get a task details
     * @param id Task id to get a task details
     * @return Task details in the form of json
     * @throws ResourceNotFoundException if no task's are present in the system
     */
    @RequestMapping(method = RequestMethod.GET, path = "/users/{userId}/tasks/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getTask(@PathVariable String id){
        try {
            int taskId = Integer.parseInt(id);
            Task task = taskService.getTask(taskId);
            return new ResponseEntity(task, HttpStatus.OK);
        }catch (IllegalArgumentException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        catch(ResourceNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get all tasks
     * @return List of all Tasks
     * @throws ResourceNotFoundException if no task's are present in the system
     */
    @RequestMapping(method = RequestMethod.GET, path = "/tasks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getTasks(){
        try {
            List<Task> tasks = taskService.getTasks();
            return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get all tasks of specified user
     * @return List of all task of a user
     * @throws ResourceNotFoundException if no task's are present in the system
     */
    @RequestMapping(method = RequestMethod.GET, path = "/users/{userId}/tasks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getTaskByUserId(@PathVariable String userId){
        try {
            int id = Integer.parseInt(userId);
            List<Task> tasks = taskService.getTasksById(id);
            ResponseEntity<List<Task>> responseEntity = new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
            return responseEntity;
        }catch (IllegalArgumentException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        catch (ResourceNotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     *  Update the task.
     * @param task Task object to update
     * @param id  Unique task id to update the task
     * @return Updated task object
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity update(@RequestBody Task task, @PathVariable String id){
        Task updatedTask = taskService.update(id, task);
        ResponseEntity<Task> responseEntity = new ResponseEntity<Task>(updatedTask, HttpStatus.ACCEPTED);
        return responseEntity;
    }

    /**
     *  Adding comment on the ticket
     * @param comment Comment to add on ticket
     * @param userId User id which is going to add comment on the task
     * @param taskId Task id to add comment on tha task
     * @return Task with all comments in json format.
     * @throws BadRequestException if comment data is invalid
     * @throws ResourceNotFoundException if provided task id is not present in the system
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/users/{userId}/tasks/{taskId}/comments", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity comment(@RequestBody Comment comment, @PathVariable String userId, @PathVariable String taskId){
        try{
            taskService.comment(comment, Integer.parseInt(userId), Integer.parseInt(taskId));
            ResponseEntity responseEntity = new ResponseEntity("Comment added on task " + taskId + " successfully", HttpStatus.ACCEPTED);
            return responseEntity;
        }catch (BadRequestException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        catch (ResourceNotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get all comments on the ticket
     * @param taskId Ticket Id to get all comments
     * @return List of all Comment objects on the given task
     * @throws BadRequestException if comment data is invalid
     * @throws ResourceNotFoundException if provided task id is not present in the system
     */
    @RequestMapping(method = RequestMethod.GET , path = "/tasks/{taskId}/comments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity comments(@PathVariable String taskId){
        try {
            List<Comment> comments = taskService.getComments(Integer.parseInt(taskId));
            return new ResponseEntity(comments, HttpStatus.OK);
        }catch (BadRequestException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        catch (ResourceNotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
