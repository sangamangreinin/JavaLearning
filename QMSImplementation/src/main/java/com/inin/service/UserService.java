package com.inin.service;

import com.inin.controllers.dto.UserRegister;
import com.inin.dao.UserDao;
import com.inin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by root on 12/5/16.
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    /**
     * Create a new producer
     * @param userRegister
     * @return an id of newly created producer
     * @throws IllegalArgumentException if the user object passed was null or invalid argument passed
     */
    public int createProducer(UserRegister userRegister){
        validateUserRegister(userRegister);
        return userDao.insert(new User(userRegister.name));
    }


    /**
     * Create a new consumer
     * @param userRegister
     * @return an id of newly created consumer
     * @throws IllegalArgumentException if the user object passed was null or invalid argument passed
     */
    public int createConsumer(UserRegister userRegister){
        validateUserRegister(userRegister);
        return userDao.insert(new User(userRegister.name));
    }

    /**
     * validate user attributes
     * @param userRegister user request object
     */
    private void validateUserRegister(UserRegister userRegister){
        if(userRegister == null){
            throw new IllegalArgumentException("User Request object passed was null");
        }
        if(userRegister.name == ""){
            throw new IllegalArgumentException("name cannot be empty");
        }
    }
}
