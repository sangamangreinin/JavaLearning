package com.inin.controllers;

/**
 * Created by root on 6/4/16.
 */

import com.inin.Error;
import com.inin.controllers.dto.CommentRequest;
import com.inin.controllers.dto.QueryRequest;
import com.inin.controllers.dto.TaskRequest;
import com.inin.model.Comment;
import com.inin.model.Task;
import com.inin.exceptions.TaskDoesNotExistException;
import com.inin.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * This class consists all the common exception and methods
 */
@RestController
public class TaskController {

    /**
     * get bean for task service
     */
    @Autowired
    TaskService taskService;

    /**
     * Common exception for task controller, The task does not exist exception will get caught here
     * @param e exception object
     * @return error response code for TaskDoesNotExistException
     */
    @ExceptionHandler(TaskDoesNotExistException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    public Error handleTaskDoesNotExistException(Exception e){
        return new Error(101, e.getMessage());
    }

    /**
     * Create Task / Draft task(if assignedId is present or not present and status is draft) for user,
     * and returns the HTTP 201[CREATED] along with a LocationHeader containing the locations of newly created task
     * @param taskRequest taskRequest object
     * @return the HTTP 201[CREATED] along with a LocationHeader containing the locations of newly created task
     */
    @RequestMapping(method = RequestMethod.POST, path = "/tasks", consumes = "application/json")
    public ResponseEntity createTask(@RequestBody TaskRequest taskRequest, UriComponentsBuilder ucBuilder){
        try{
            int id = taskService.createTask(taskRequest);

            //Location Header containing the locations of newly created task
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/tasks/{id}").buildAndExpand(id).toUri());

            return new ResponseEntity(headers, HttpStatus.CREATED);
        }catch (DataAccessException e){
            return new ResponseEntity("some error occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get list of all Draft task
     * @return the httpstatus OK[200], the list of task depending on search criteria
     */
    @RequestMapping(method = RequestMethod.POST, path = "/tasks/query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getListQuery(@RequestBody QueryRequest queryRequest){
        try {
            List<Task> tasks = taskService.query(queryRequest);
            return new ResponseEntity(tasks, HttpStatus.OK);
        }catch (DataAccessException e){
            return new ResponseEntity("No Records found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * add comment to a task
     * @param commentRequest comment on task in object
     * @param taskId task id in int
     * @return the httpstatus OK[200] if comment added successfully
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/tasks/{taskId}/comments", consumes = "application/json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity addCommentToTask(@RequestBody CommentRequest commentRequest, UriComponentsBuilder uriComponentsBuilder, @PathVariable int taskId){
        try {
            int commentId = taskService.addCommentToTask(taskId, commentRequest);

            //Location Header containing the locations of newly created task
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(uriComponentsBuilder.path("/comments/{id}").buildAndExpand(commentId).toUri());

            return new ResponseEntity(headers, HttpStatus.OK);
        }catch (DataAccessException e){
            return new ResponseEntity("some error occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get list of comments on a particular task
     * @param taskId task id in int
     * @return the httpstatus OK[200], list of comments
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
     * @param taskRequest task object
     * @param taskId task id in int
     * @return the httpstatus OK[200] if task updated successfully
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/tasks/{taskId}", consumes = "application/json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateTask(@RequestBody TaskRequest taskRequest, @PathVariable int taskId){
        try{
            taskService.updateTask(taskId, taskRequest);
            return new ResponseEntity(HttpStatus.OK);
        }catch (DataAccessException e){
            return new ResponseEntity("Some error occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}