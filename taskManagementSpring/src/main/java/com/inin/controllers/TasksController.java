package com.inin.controllers;

/**
 * Created by root on 6/4/16.
 */

import com.inin.Error;
import com.inin.domain.Comment;
import com.inin.domain.Task;
import com.inin.domain.User;
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
     * Common exception for task controller, The illegal argument exception will get caught here
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleCommonIllegalArgumentExceptions(Exception e){
        return new Error(100, e.getMessage());
    }

    /**
     * Common exception for task controller, The task does not exist exception will get caught here
     * @param e
     * @return
     */
    @ExceptionHandler(TaskDoesNotExistException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    public Error handleUserDoesNotExistException(Exception e){
        return new Error(101, e.getMessage());
    }

    /**
     * Create user
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/users", consumes = "application/json")
    public ResponseEntity createUser(@RequestBody User user){
        int id = taskService.createUser(user);
        return new ResponseEntity(id, HttpStatus.CREATED);
    }

    /**
     * Get user by Id
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/users/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getUserById(@PathVariable("id") int id){
        try {
            User foundUser = taskService.findUserById(id);
            return new ResponseEntity(foundUser, HttpStatus.OK);
        }catch (DataAccessException d){
            //if the user object not found then throw 404 error
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Create Task / Draft task(if assignedId is not present) for user
     * @param task
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/tasks", consumes = "application/json")
    public ResponseEntity createTask(@RequestBody Task task){
        int id = taskService.createTask(task);
        return new ResponseEntity(id, HttpStatus.CREATED);
    }

    /**
     * Get list of all task for a particular user
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/users/{id}/tasks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getListOfTaskByUserId(@PathVariable int id){
        try {
            List<Task> tasks = taskService.getListOfTaskByUserId(id);
            return new ResponseEntity(tasks, HttpStatus.OK);
        }catch (DataAccessException d){
            //if the user object not found then throw 404 error
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get list of all Draft task
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/tasks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getListOfDraftTask(){
        List<Task> tasks = taskService.getListOfAllDraftTask();
        return new ResponseEntity(tasks, HttpStatus.OK);
    }

    /**
     * add comment to a task
     * @param comment
     * @param taskId
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/tasks/{taskId}/comments", consumes = "application/json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity addCommentToTask(@RequestBody Comment comment, @PathVariable int taskId){
        taskService.isTaskExist(taskId);
        taskService.addCommentToTask(taskId, comment);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Get list of comments on a particular task
     * @param taskId
     * @return list of comments
     */
    @RequestMapping(method = RequestMethod.GET, path = "/tasks/{taskId}/comments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getListOfComments(@PathVariable int taskId){
        taskService.isTaskExist(taskId);
        List<Comment> comments = taskService.getListOfComments(taskId);
        return new ResponseEntity(comments, HttpStatus.OK);
    }

    /**
     * Update task status and postpone task date
     */

    @RequestMapping(method = RequestMethod.PUT, path = "/tasks/{taskId}", consumes = "application/json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateTask(@RequestBody Task task, @PathVariable int taskId){
        taskService.isTaskExist(taskId);
        taskService.updateTask(taskId, task);
        return new ResponseEntity(HttpStatus.OK);
    }
}
