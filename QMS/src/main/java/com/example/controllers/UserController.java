package com.example.controllers;

import com.example.controllers.dto.UserRequest;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by root on 12/5/16.
 */
@RestController
@RequestMapping(path = "api/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public void createUser(@RequestBody UserRequest userRequest){
        System.out.println("in controller");
        userService.createUser(userRequest);
    }
}
