package com.inin.controller;

import com.inin.dto.UserDTO;
import com.inin.service.UserService;
import com.inin.util.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by root on 6/4/16.
 */
@RestController
@RequestMapping(path = "/api/V1/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Success> createUser(@RequestBody UserDTO user){
        int id = userService.create(user);
        String message = "User created with the above mentioned Id.";
        return new ResponseEntity(new Success(id, message), HttpStatus.CREATED);
    }
}
