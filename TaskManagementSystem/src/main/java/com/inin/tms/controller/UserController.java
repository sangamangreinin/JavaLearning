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
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, path = "/books", consumes = "application/json", produces = "application/json")
    public ResponseEntity createUser(@RequestBody User user ){
        userService.createUser(user);
        ResponseEntity<User> userResponse = new ResponseEntity<User>(user, HttpStatus.CREATED);
        return userResponse;
    }
}
