package com.inin.controller;

import com.inin.controller.dto.CommentRequest;
import com.inin.controller.dto.QueryRequest;
import com.inin.controller.dto.TaskRequest;
import com.inin.model.Comment;
import com.inin.model.Task;
import com.inin.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by root on 6/4/16.
 */
@Controller
@RequestMapping(value = "/api/v1/tasks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TaskController {
    private static final String TASKS = "/tasks";
    private static final String TASK = TASKS + "/{id}";

    @Autowired
    private TaskService taskService;

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleHttpMessageNotReadableException(Exception e) {
        return "Invalid arguments provided";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleIllegalArgumentException(Exception e) {
        return e.getMessage();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createTask(@RequestBody TaskRequest taskRequest, UriComponentsBuilder uriComponentsBuilder) {
        try {
            int taskId = taskService.createTask(taskRequest);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(uriComponentsBuilder.path(TASK).buildAndExpand(taskId).toUri());
            String responseBody = "Task created with id as " + taskId;
            return new ResponseEntity(responseBody, httpHeaders, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity("some error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{taskId}")
    public ResponseEntity getTask(@PathVariable("taskId") int taskId) {
        try {
            Task task = taskService.getTask(taskId);
            return new ResponseEntity(task, HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity("Invalid taskId", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{taskId}")
    public ResponseEntity updateTask(@PathVariable("taskId") int taskId, @RequestBody TaskRequest taskRequest) {
        try {
            taskService.updateTask(taskId, taskRequest);
            return new ResponseEntity(HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity("Invalid taskId for Update", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{taskId}/comments")
    public ResponseEntity addComment(@PathVariable("taskId") int taskId, @RequestBody CommentRequest commentRequest) {
        try {
            int commentId = taskService.addComment(taskId, commentRequest);
            String message = "Comment created with id as " + commentId;
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity("some error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{taskId}/comments")
    public ResponseEntity viewComments(@PathVariable("taskId") int taskId) {
        try {
            List<Comment> comments = taskService.getComments(taskId);
            return new ResponseEntity(comments, HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity("No comments found", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/query")
    public ResponseEntity viewDrafts(@RequestHeader("userId") int userId, @RequestBody QueryRequest queryRequest) {
        try {
            List<Task> tasks = taskService.search(userId, queryRequest);
            return new ResponseEntity(tasks, HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity("No drafts found", HttpStatus.NOT_FOUND);
        }
    }
}
