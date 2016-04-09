package com.inin.controllers;

import com.inin.domain.User;
import com.inin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by evansbelly on 6/4/16.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody User user) {
        try {
            userService.create(user);
        } catch (IllegalArgumentException ill) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
