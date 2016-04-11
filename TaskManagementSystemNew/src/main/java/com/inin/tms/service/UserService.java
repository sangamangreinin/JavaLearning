package com.inin.tms.service;

import com.inin.tms.dao.UserDao;
import com.inin.tms.domain.User;
import com.inin.tms.exception.ResourceCreationFailedException;
import com.inin.tms.repositary.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by root on 2/4/16.
 * Defines the behaviour of the UserService class
 */
@Service
public class UserService extends BaseService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDao userDao;
    /**
     * Creating a new user
     * @param user User Object
     * @return User id
     */
    public int createUser(User user){
        if(user == null)
            throw new IllegalArgumentException("User object cant be null");

        validate(user.getFirstName(), "firstName");
        validate(user.getEmail(), "email id");

        int id = userDao.save(user);
        if(id == 0 )
         throw  new ResourceCreationFailedException("User Creation failed");
        return id;
    }
}
