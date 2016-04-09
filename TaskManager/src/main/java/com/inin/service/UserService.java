package com.inin.service;

import com.inin.dao.UserDao;
import com.inin.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by evansbelly on 6/4/16.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * create service for a user
     * @param user
     * @throws IllegalArgumentException if the user object is null
     */
    public void create(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User can not be empty");
        }
        userDao.insert(user);
    }
}
