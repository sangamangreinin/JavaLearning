package com.inin.tms.controller;

import com.inin.tms.domain.User;
import com.inin.tms.exception.ResourceCreationFailedException;
import com.inin.tms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by root on 2/4/16.
 * This UserController class interact between user & service.
 */
@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Creating a new User
     * @param user User object
     * @return User id with CREATED status code
     * @throws ResourceCreationFailedException if user is not created into the system
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity createUser(@RequestBody User user ){
        try {
            int id = userService.createUser(user);
            return new ResponseEntity("User " + id + " is created successfully!", HttpStatus.CREATED);
        }
        catch (IllegalArgumentException e)
        {
            return  new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        catch (ResourceCreationFailedException e){
            return  new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
