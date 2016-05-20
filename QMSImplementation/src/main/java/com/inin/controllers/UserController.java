package com.inin.controllers;

import com.inin.controllers.dto.UserRegister;
import com.inin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(path = "api", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = "application/json")
public class UserController {

    /**
     * get bean for user service
     */
    @Autowired
    UserService userService;

    /**
     * Create producer,
     * and returns the HTTP 201[CREATED] along with a LocationHeader containing the locations of newly created producer
     * @param userRegister user Register object
     * @return the HTTP 201[CREATED] along with a LocationHeader containing the locations of newly created producer
     */
    @RequestMapping(method = RequestMethod.POST, path = "/producers")
    public ResponseEntity registerAsProducer(@RequestBody UserRegister userRegister, UriComponentsBuilder ucBuilder){
        int id = userService.createProducer(userRegister);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(id).toUri());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    /**
     * Create consumer,
     * and returns the HTTP 201[CREATED] along with a LocationHeader containing the locations of newly created consumer
     * @param userRegister user Register object
     * @return the HTTP 201[CREATED] along with a LocationHeader containing the locations of newly created consumer
     */
    @RequestMapping(method = RequestMethod.POST, path = "/consumers")
    public ResponseEntity registerAsConsumer(@RequestBody UserRegister userRegister, UriComponentsBuilder ucBuilder){
        int id = userService.createConsumer(userRegister);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(id).toUri());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }
}
