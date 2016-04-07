package com.inin.tms.controller;

import com.inin.tms.domain.User;
import com.inin.tms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by root on 2/4/16.
 * This UserController class interact between user & service.
 */
@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Creating a new User
     * @param user User object
     * @return User object with CREATED status code
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity createUser(@RequestBody User user ){
        User newUser =  userService.createUser(user);
        ResponseEntity<User> userResponse = new ResponseEntity<User>(newUser, HttpStatus.CREATED);
        return userResponse;
    }

    /**
     * Update a user
     * @param user User object to be saved
     * @param id User id
     * @return User object in json format with http status ACCEPTED code
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/{userId}")
    public ResponseEntity updateUser(@RequestBody User user, @PathVariable String id){
        User userObj = userService.updateUser(user);
        ResponseEntity<User> userResponse = new ResponseEntity<User>(userObj, HttpStatus.ACCEPTED);
        return userResponse;
    }
}
