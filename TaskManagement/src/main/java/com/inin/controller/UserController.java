package com.inin.controller;

import com.inin.controller.dto.UserRequest;
import com.inin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by root on 6/4/16.
 */
@Controller
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {
    private static final String USERS = "/users";
    private static final String USER = USERS + "/{id}";

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createTask(@RequestBody UserRequest userRequest, UriComponentsBuilder uriComponentsBuilder) {
        int userId = userService.createUser(userRequest);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uriComponentsBuilder.path(USER).buildAndExpand(userId).toUri());
        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }
}
