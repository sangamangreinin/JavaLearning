package com.inin.controllers;

import com.inin.Response;
import com.inin.domain.Comment;
import com.inin.domain.Task;
import com.inin.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by evansbelly on 6/4/16.
 */
@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * create a task
     * @param task : task entities / Request body
     * @return Response entity. 201 : Created or 400 : Bad request
     */
    @RequestMapping(method = RequestMethod.POST, path = "/tasks", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity create(@RequestBody Task task) {
        int taskId = 0;
        try {
            taskId = taskService.create(task);
        } catch (IllegalArgumentException ill) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(new Response(taskId, "New Task created"), HttpStatus.CREATED);
    }

    /**
     * Find task for a user
     * @param userId
     * @return List of task for a specified user
     */
    @RequestMapping(method = RequestMethod.GET, path = "/users/{userId}/tasks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Task>> findTaskByUser(@PathVariable int userId) {
        try {
            List<Task> task = taskService.findTaskByUser(userId);
            return new ResponseEntity(task, HttpStatus.OK);
        } catch (EmptyResultDataAccessException emp) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Add a comment to a task
     * @param comment
     * @param taskId
     */
    @RequestMapping(method = RequestMethod.POST, path = "/tasks/{taskId}/comments", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity comment(@RequestBody Comment comment, @PathVariable int taskId) {
        try {
            taskService.comment(comment, taskId);
        } catch (IllegalArgumentException ill) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     * update the task (Status)
     * @param task
     * @param taskId
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/tasks/{taskId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<Task> update(@RequestBody Task task, @PathVariable int taskId) {
        try {
            Task updatedTask = taskService.update(task, taskId);
            return new ResponseEntity<Task>(updatedTask,HttpStatus.OK);
        } catch (IllegalArgumentException ill) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Find task by its task id
     * @param taskId
     * @return List of task for a specified user
     */
    @RequestMapping(method = RequestMethod.GET, path = "/tasks/{taskId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Task> findTask(@PathVariable int taskId) {
        try {
            Task task = taskService.findTask(taskId);
            return new ResponseEntity(task, HttpStatus.OK);
        } catch (EmptyResultDataAccessException emp) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * search on the basis of status
     * @param search
     * @return List of tasks with status given
     */
    @RequestMapping(method = RequestMethod.GET, path = "/tasks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Task>> search(@RequestParam(value = "status", required = true) String search) {
        try {
            List<Task> task = taskService.search(search);
            return new ResponseEntity(task, HttpStatus.OK);
        } catch (EmptyResultDataAccessException emp) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Postpone task
     * @param task
     * @param taskId
     * @return the postponed task
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/tasks/{taskId}/reschedule", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<Task> postpone(@RequestBody Task task, @PathVariable int taskId) {
        try {
            Task updatedTask = taskService.update(task, taskId);
            return new ResponseEntity<Task>(updatedTask,HttpStatus.OK);
        } catch (IllegalArgumentException ill) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * comments for a specific task id
     * @param taskId
     * @return List of comments
     */
    @RequestMapping(method = RequestMethod.GET, path = "/tasks/{taskId}/comments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Comment>> comments(@PathVariable int taskId) {
        try {
            List<Comment> comments = taskService.comments(taskId);
            return new ResponseEntity(comments, HttpStatus.OK);
        } catch (EmptyResultDataAccessException emp) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}