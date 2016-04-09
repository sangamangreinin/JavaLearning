package com.inin.controller;

import com.inin.domain.Comment;
import com.inin.domain.Task;
import com.inin.dto.TaskDTO;
import com.inin.service.TaskService;
import com.inin.util.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by root on 6/4/16.
 */
@RestController
@RequestMapping(path = "/api/V1/tasks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     *
     * @param taskDTO - maps the request body with TaskDTO
     * @return - ResponseEntity containing HTTP_STATUS : 201 CREATED or 400 BAD_REQUEST
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Success> createTask(@RequestBody TaskDTO taskDTO){
        int id = taskService.createTask(taskDTO);
        String message = "Task created with the above mentioned Id.";
        return new ResponseEntity<Success>(new Success(id, message),HttpStatus.CREATED);
    }

    /**
     *
     * @param comment comment object from the user containing userId and string message
     * @param id - task Id
     * @return -ResponseEntity containing HTTP_STATUS : 201 CREATED or 400 BAD_REQUEST
     */
    @RequestMapping(method = RequestMethod.POST, path = "/{id}/comments", consumes = "application/json")
    public ResponseEntity addComment(@RequestBody Comment comment, @PathVariable int id){
        taskService.addComment(comment, id);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     *
     * @param taskDTO - request param from user
     * @param id - task Id in integer
     * @param userId - user id passed in header
     * @return Task model object
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<Task> updateTask(@RequestBody TaskDTO taskDTO, @PathVariable int id, @RequestHeader ("user-id") int userId){
        return new ResponseEntity<Task>(taskService.updateTask(taskDTO, id, userId), HttpStatus.ACCEPTED);
    }

    /**
     *
     * @param status string status sent by the user
     * @param userId - integer user id from header
     * @return List of Task model object
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getDraftTasks(@RequestParam("status") String status,@RequestHeader ("user-id") int userId){
        return new ResponseEntity<List<Task>>(taskService.getAllDraftTasks(status, userId),HttpStatus.OK);
    }

    /**
     *
     * @param id - integer task id
     * @param userId integer user id from the header
     * @return List of Comment model object
     */
    @RequestMapping(path = "/{id}/comments", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> getCommentsOnTask(@PathVariable int id, @RequestHeader ("user-id") int userId){
        return new ResponseEntity<List<Comment>>(taskService.getComments(id, userId),HttpStatus.OK);
    }
}
