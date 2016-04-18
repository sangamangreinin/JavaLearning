package com.inin.controllers;

import com.inin.domain.User;
import com.inin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * Create user
     * @param user
     * @return id of the newly created user
     */
    @RequestMapping(method = RequestMethod.POST, path = "/users", consumes = "application/json")
    public ResponseEntity createUser(@RequestBody User user){
        try{
            int id = userService.createUser(user);
            return new ResponseEntity(id, HttpStatus.CREATED);
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
