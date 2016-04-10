package com.inin.service;

import com.inin.controller.dto.UserRequest;
import com.inin.dao.UserDAO;
import com.inin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by root on 10/4/16.
 */
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    /**
     * create user
     *
     * @param userRequest UserRequest DTO
     * @return int id of the newly created User
     */
    public int createUser(UserRequest userRequest) {
        String name = userRequest.name;
        String gender = userRequest.gender;

        User user = new User(name, gender.charAt(0));
        return userDAO.createUser(user);
    }
}
