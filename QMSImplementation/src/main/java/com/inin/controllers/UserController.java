package com.inin.controllers;

import com.inin.controllers.dto.UserRequest;
import com.inin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by root on 12/5/16.
 */
@RestController
@RequestMapping(path = "api/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

    /**
     * get bean for user service
     */
    @Autowired
    UserService userService;

    /**
     * Create user(producer and consumer),
     * and returns the HTTP 201[CREATED] along with a LocationHeader containing the locations of newly created task
     * @param userRequest user Request object
     * @return the HTTP 201[CREATED] along with a LocationHeader containing the locations of newly created task
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity createUser(@RequestBody UserRequest userRequest, UriComponentsBuilder ucBuilder){
        int id = userService.createUser(userRequest);
        //Location Header containing the locations of newly created user
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(id).toUri());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }
}
