package com.inin.controllers;

import com.inin.controllers.dto.UserRequest;
import com.inin.domain.User;
import com.inin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by root on 18/4/16.
 */

@RestController
public class UsersController {
    /**
     * get bean for user service
     */
    @Autowired
    UserService userService;

    /**
     * creates a user, and returns the HTTP 201[CREATED] along with a LocationHeader containing the locations of newly created user
     * @param userRequest
     * @return the HTTP 201[CREATED] along with a LocationHeader containing the locations of newly created user
     */
    @RequestMapping(method = RequestMethod.POST, path = "/users", consumes = "application/json")
    public ResponseEntity createUser(@RequestBody UserRequest userRequest, UriComponentsBuilder ucBuilder){
        try{
            int id = userService.createUser(userRequest);

            //Location Header containing the locations of newly created user
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(id).toUri());

            return new ResponseEntity(headers, HttpStatus.CREATED);
        }catch (DataAccessException e){
            return new ResponseEntity("Database server disconnected", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get user by Id
     * @param id
     * @return the user object
     */
    @RequestMapping(method = RequestMethod.GET, path = "/users/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getUserById(@PathVariable("id") int id){
        try {
            User foundUser = userService.findUserById(id);
            return new ResponseEntity(foundUser, HttpStatus.OK);
        }catch (DataAccessException d){
            return new ResponseEntity("Invalid userId",HttpStatus.NOT_FOUND);
        }
    }
}
