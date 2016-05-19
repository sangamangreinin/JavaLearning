package com.inin.service;

import com.inin.controllers.dto.UserRequest;
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
     * Create a new user
     * @param userRequest
     * @return an id of newly created user
     * @throws IllegalArgumentException if the user object passed was null or invalid argument passed
     */
    public int createUser(UserRequest userRequest){
        validateUserRequest(userRequest);
        return userDao.insert(new User(userRequest.name));
    }

    /**
     * validate user attributes
     * @param userRequest user request object
     */
    private void validateUserRequest(UserRequest userRequest){
        if(userRequest == null){
            throw new IllegalArgumentException("User Request object passed was null");
        }
        if(userRequest.name == ""){
            throw new IllegalArgumentException("name cannot be empty");
        }
    }
}
