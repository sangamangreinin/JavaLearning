package com.inin.service;

import com.inin.Util;
import com.inin.controllers.dto.UserRequest;
import com.inin.dao.UserDao;
import com.inin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class consist of all the service related to user
 */
@Service
public class UserService {

    /**
     * get bean of user dao
     */
    @Autowired
    private UserDao userDao;

    /**
     * Create a new user
     * @param userRequest
     * @return an id of newly created user
     * @throws IllegalArgumentException if the user object passed was null
     */

    public int createUser(UserRequest userRequest){
        validateUser(userRequest);
        return userDao.insert(new User(userRequest.name));
    }

    /**
     * get user by id
     * @param id
     * @return the user object
     * @throws if the userId passed is negative or 0
     */

    public User findUserById(int id){
        if(id <= 0 ){
            throw new IllegalArgumentException("User Id cannot be 0 or less than 0");
        }
        return userDao.findById(id);
    }

    /**
     * validate user attributes
     * @param userRequest user request object
     */
   private void validateUser(UserRequest userRequest){
        if(userRequest == null){
            throw new IllegalArgumentException("User Request object passed was null");
        }
        if(Util.isInValidString(userRequest.name)){
            throw new IllegalArgumentException("error name required");
        }
    }
}
