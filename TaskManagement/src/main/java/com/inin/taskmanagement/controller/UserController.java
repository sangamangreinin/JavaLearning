package com.inin.taskmanagement.controller;

import com.inin.taskmanagement.domain.User;
import com.inin.taskmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Manish Dubey on 5/4/16.
 * Rest end point of User service of task management
 */
@RestController
@RequestMapping(path = "/users")
public class UserController {

    /**
     * User service used for interacting user dao
     */
    @Autowired
    private UserService userService;

    /**
     * Crete new user rest end point
     * @param user
     * @return success message to user
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@RequestBody User user){
        userService.createUser(user);
        return "User Created Successfully";
    }

    /**
     * Return the user date of passed user id
     * @param id
     * @return user data
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User getUser(@PathVariable("id") long id){
        return userService.getUser(id);
    }

    /**
     * Return the list of user
     * @return list of user
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<User> getUsers(){
        return userService.getUsers();
    }
}
