package com.inin.controllers;

/**
 * Created by root on 6/4/16.
 */

import com.inin.Error;
import com.inin.domain.Comment;
import com.inin.domain.Task;
import com.inin.exceptions.TaskDoesNotExistException;
import com.inin.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class consists all the common exception and methods
 */
@RestController
public class TasksController {

    /**
     * get bean for task service
     */
    @Autowired
    TaskService taskService;

    /**
     * Common exception for task controller, The task does not exist exception will get caught here
     * @param e
     * @return error response code for TaskDoesNotExistException
     */
    @ExceptionHandler(TaskDoesNotExistException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    public Error handleTaskDoesNotExistException(Exception e){
        return new Error(101, e.getMessage());
    }

    /**
     * Create Task / Draft task(if assignedId is not present) for user
     * @param task
     * @return the id of the newly created task
     */
    @RequestMapping(method = RequestMethod.POST, path = "/tasks", consumes = "application/json")
    public ResponseEntity createTask(@RequestBody Task task){
        try{
            int id = taskService.createTask(task);
            return new ResponseEntity(id, HttpStatus.CREATED);
        }catch (DataAccessException e){
            return new ResponseEntity("Database server disconnected", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get list of all task for a particular user
     * @param id
     * @return the list of task by user id
     */
    @RequestMapping(method = RequestMethod.GET, path = "/users/{id}/tasks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getListOfTaskByUserId(@PathVariable int id){
        try {
            List<Task> tasks = taskService.getListOfTaskByUserId(id);
            return new ResponseEntity(tasks, HttpStatus.OK);
        }catch (DataAccessException d){
            return new ResponseEntity("Invalid userId",HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get list of all Draft task
     * @return the list of all draft task
     */
    @RequestMapping(method = RequestMethod.GET, path = "/tasks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getListOfDraftTask(){
        try {
            List<Task> tasks = taskService.getListOfAllDraftTask();
            return new ResponseEntity(tasks, HttpStatus.OK);
        }catch (DataAccessException e){
            return new ResponseEntity("No Draft Task found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * add comment to a task
     * @param comment
     * @param taskId
     * @return the httpstatus if comment added successfully
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/tasks/{taskId}/comments", consumes = "application/json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity addCommentToTask(@RequestBody Comment comment, @PathVariable int taskId){
        try {
            taskService.addCommentToTask(taskId, comment);
            return new ResponseEntity(HttpStatus.OK);
        }catch (DataAccessException e){
            return new ResponseEntity("Database server disconnected", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get list of comments on a particular task
     * @param taskId
     * @return list of comments
     */
    @RequestMapping(method = RequestMethod.GET, path = "/tasks/{taskId}/comments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getListOfComments(@PathVariable int taskId){
        try {
            List<Comment> comments = taskService.getListOfComments(taskId);
            return new ResponseEntity(comments, HttpStatus.OK);
        }catch (DataAccessException e){
            return new ResponseEntity("No comments found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update task status and postpone task date
     * @param task
     * @param taskId
     * @return the httpstatus if task updated successfully
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/tasks/{taskId}", consumes = "application/json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateTask(@RequestBody Task task, @PathVariable int taskId){
        try{
            taskService.updateTask(taskId, task);
            return new ResponseEntity(HttpStatus.OK);
        }catch (DataAccessException e){
            return new ResponseEntity("Database server disconnected", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}