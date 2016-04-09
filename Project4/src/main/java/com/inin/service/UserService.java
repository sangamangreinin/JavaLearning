package com.inin.service;

import com.inin.dao.UserDAO;
import com.inin.domain.User;
import com.inin.dto.UserDTO;
import com.inin.exception.OnCreateException;
import com.inin.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by root on 6/4/16.
 */
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    /**
     * creates a new user which maps the dto object to actual db object and sends it to DAO for creation
     * @param user - user DTo object requested by the user
     * @throws IllegalArgumentException if data is not passed correctly
     * @throws OnCreateException if failed to create a new resource
     */
    public int create(UserDTO user){
        if (!Util.checkObjectNull(user))
            throw new IllegalArgumentException("Please check the date you have sent.");

        if (!Util.checkValidString(user.name) || !Util.checkValidString(user.gender+"") ||
                !Util.checkValidString(user.email) || !Util.checkValidString(user.username) || !Util.checkValidString(user.password)){
            throw new IllegalArgumentException("Please check the data sent through params");
        }
        User u = new User(user.name, user.gender, user.email, user.username, user.password);

        int id = userDAO.insert(u);
        if (!Util.checkValidInteger(id)){
            throw new OnCreateException("Operation failed.");
        }
        return id;
    }
}
