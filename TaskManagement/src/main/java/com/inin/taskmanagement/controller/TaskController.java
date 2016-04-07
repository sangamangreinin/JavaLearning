package com.inin.taskmanagement.controller;

import com.inin.taskmanagement.domain.Comment;
import com.inin.taskmanagement.domain.Task;
import com.inin.taskmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Manish Dubey on 5/4/16.
 * Task Controller act task end point
 */
@RestController
@RequestMapping(path = "/tasks")
public class TaskController {
    /**
     * Task service interacting with task dao
     */
    @Autowired
    private TaskService taskService;

    /**
     * Create new task into system
     * @param task
     * @return success message to the user
     */
    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String create(@RequestBody  Task task){
        taskService.create(task);
        return "Task created successfully";
    }

    /**
     * Get task by Id
     * @param id
     * @return task if valid id is provided
     */
    @RequestMapping(method = RequestMethod.GET,path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Task getTask(@PathVariable("id") long id){
        return taskService.getTask(id);
    }

    /**
     * Return user's list of task
     * @param userId
     * @param status
     * @return list of task
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Task> getTasks(@RequestParam(value = "userId", required = true) long userId,@RequestParam(value = "status",required = false) String status){
        return taskService.getTasks(userId,status);
    }

    /**
     * Update task with status , taskDoerId, dueDate
     * @param id
     * @param task
     * @return updated task details
     */
    @RequestMapping(method = RequestMethod.PUT,path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Task update(@PathVariable("id") long id, @RequestBody Task task){
        return taskService.update(id,task);
    }

    /**
     * Comments on Task
     * @param id
     * @param comment
     * @return success message
     */
    @RequestMapping(method = RequestMethod.POST,path = "/{id}/comments", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String comments(@PathVariable("id") long id,@RequestBody Comment comment){
        taskService.comments(id, comment);
        return "Comment added successfully";
    }

    /**
     * Get all comments of task
     * @param id
     * @return list of all comments on task
     */
    @RequestMapping(method = RequestMethod.GET,path = "/{id}/comments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Comment> comments (@PathVariable("id") long id,@RequestParam(value = "userId", required = true) long userId){
        return taskService.getComments(id,userId);
    }
}
