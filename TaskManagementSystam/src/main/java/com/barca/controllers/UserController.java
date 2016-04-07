package com.barca.controllers;

import com.barca.model.User;
import com.barca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by root on 3/4/16.
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, path = "/users")
    public Callable<ResponseEntity> createUser(@RequestBody User user) {
        return () -> userService.createUser(user);
    }


    @RequestMapping(method = RequestMethod.GET, path = "/users/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Callable<User> getUser(@PathVariable("id") int userId) {
        return () -> userService.getUser(userId);
    }
}
