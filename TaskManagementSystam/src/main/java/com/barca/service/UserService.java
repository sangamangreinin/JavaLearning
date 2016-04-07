package com.barca.service;

import com.barca.Util.Util;
import com.barca.dao.UserDao;
import com.barca.exception.UserNotFoundException;
import com.barca.model.Task;
import com.barca.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Created by root on 3/4/16.
 */

@Service
public class UserService {

    @Autowired
    private UserDao userDao;


    public ResponseEntity createUser(User user) {
        validation(user);
        userDao.insert(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }


    public User getUser(long userId) throws DataAccessException {

       return   userDao.findUser(userId);
    }



    /**
     * validate user object
     *
     * @param user
     * @throws IllegalArgumentException if name and email should be null and empty
     */
    private void validation(User user) throws IllegalArgumentException {

        if(!Util.isInValidString(user.getName()))
            throw new IllegalArgumentException("Name should be null or empty");
       if(!Util.isInValidString(user.getEmail()));
        throw new IllegalArgumentException("Email should be null or empty");

    }

}
