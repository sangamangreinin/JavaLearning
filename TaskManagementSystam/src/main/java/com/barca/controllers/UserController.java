package com.barca.controllers;

import com.barca.model.User;
import com.barca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;

/**
 * Created by root on 3/4/16.
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * create user in the system
     *
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/users")
    public Callable<ResponseEntity> createUser(@RequestBody User user) {
        return () -> userService.createUser(user);
    }


    /**
     * get the user by user Id from the system
     *
     * @param userId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/users/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Callable<User> getUser(@PathVariable("id") int userId) {
        return () -> userService.getUser(userId);
    }

}
