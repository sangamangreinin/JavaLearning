package com.inin.controller;

import com.inin.domain.SystemUser;
import com.inin.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Deepak on 2/4/16.
 * This represents controller class for SystemUser.
 * It contains all the controller function related to system user.
 */

@RestController
@RequestMapping(path = "/users")
public class SystemUserController {

    @Autowired
    private SystemUserService userService;


    /**
     * This is controller method to create the user in system
     * @param systemUser is system user object which is stored in system.
     * */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<SystemUser> create(@RequestBody SystemUser systemUser){

        userService.createUser(systemUser);
        ResponseEntity<SystemUser> responseEntity = new ResponseEntity<SystemUser>(systemUser, HttpStatus.CREATED);
        return responseEntity;
    }


    /**
     * This is controller method to get the all users in system.
     * */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<SystemUser>> get(){

        ResponseEntity<List<SystemUser>> entity = new ResponseEntity<List<SystemUser>>(userService.get(), HttpStatus.OK);
        return entity;
    }


    /**
     * This is controller method to get the specific user in system on the basis of id provided in url path.
     * */
    @RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = "application/json")
    public ResponseEntity<SystemUser> get(@PathVariable("id") int userId){
        SystemUser systemUser = userService.get(userId);
        if(systemUser == null){
            return new ResponseEntity<SystemUser>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<SystemUser>(systemUser, HttpStatus.OK);
    }


    /**
     * This is controller update method to update the user info on the basis of id provided in url path.
     * One can update only the name, username and password but not email.
     * */
    @RequestMapping(method = RequestMethod.PUT, path = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SystemUser> update(@PathVariable("id") int userId, @RequestBody SystemUser systemUser){
        SystemUser systemUser1 = userService.update(userId, systemUser);

        if(systemUser == null){
            return new ResponseEntity<SystemUser>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<SystemUser>(systemUser, HttpStatus.ACCEPTED);
    }
}
