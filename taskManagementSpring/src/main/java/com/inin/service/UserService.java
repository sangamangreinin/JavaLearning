package com.inin.service;

import com.inin.dao.UserDaoImpl;
import com.inin.domain.User;
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
    private UserDaoImpl userDaoImpl;

    /**
     * create a new user
     * @param user
     * @return an id of newly created user
     * @throws IllegalArgumentException if the user object passed was null
     */

    public int createUser(User user){
        if(user == null){
            throw new IllegalArgumentException("User object passed was null");
        }
        return userDaoImpl.insert(user);
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
        return userDaoImpl.findById(id);
    }
}
