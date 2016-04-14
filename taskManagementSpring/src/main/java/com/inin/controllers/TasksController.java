package com.inin.controllers;

/**
 * Created by root on 6/4/16.
 */

import com.inin.Error;
import com.inin.domain.Task;
import com.inin.domain.User;
import com.inin.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TasksController {

    @Autowired
    TaskService taskService;

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleCommonIllegalArgumentExceptions(Exception e){
        //here the illegal argument exception will get caught
        return new Error(100, e.getMessage());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/users", consumes = "application/json")
    public ResponseEntity createUser(@RequestBody User user){
        System.out.println(user);
        int id = taskService.createUser(user);
        return new ResponseEntity(id, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/users/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getUserById(@PathVariable("id") int id){
        try {
            User foundUser = taskService.findUserById(id);
            System.out.println(foundUser);
            return new ResponseEntity(foundUser, HttpStatus.OK);
        }catch (DataAccessException d){
            //if the user object not found then throw 404 error
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/tasks", consumes = "application/json")
    public ResponseEntity createTask(@RequestBody Task task){
        System.out.println(task);
        int id = taskService.createTask(task);
        return new ResponseEntity(id, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/users/{id}/tasks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Task>> getListOfTaskByUserId(@PathVariable int id){
        System.out.println(id);
        try {
            List<Task> tasks = taskService.getListOfTaskByUserId(id);
            System.out.println(tasks);
            return new ResponseEntity(tasks, HttpStatus.OK);
        }catch (DataAccessException d){
            //if the user object not found then throw 404 error
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
