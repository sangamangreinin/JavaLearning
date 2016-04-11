package com.barca.service;

import com.barca.dao.UserDao;
import com.barca.exception.UserNotFoundException;
import com.barca.model.User;
import com.barca.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * create the user in  system
     *
     * @param user
     * @return created as response
     */
    public ResponseEntity createUser(User user) {
        validation(user);
        userDao.insert(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }


    /**
     * get the user by Id
     *
     * @param userId
     * @return
     * @throws UserNotFoundException
     */
    public User getUser(long userId) throws UserNotFoundException {
        if (userDao.isUserIdAlreadyExist(userId)) {
            return userDao.findUser(userId);
        }
        throw new UserNotFoundException("user Not Found");
    }


    /**
     * validate user object
     *
     * @param user
     * @throws IllegalArgumentException if name and email should be null and empty
     */
    private void validation(User user) throws IllegalArgumentException {

        if (Util.isInValidString(user.getName()))
            throw new IllegalArgumentException("Name should be null or empty");
        if (Util.isInValidString(user.getEmail())) ;
        throw new IllegalArgumentException("Email should be null or empty");

    }

}
