package com.example.service;

import com.example.controllers.dto.UserRequest;
import com.example.dao.UserDao;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by root on 12/5/16.
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public void createUser(UserRequest userRequest){
        System.out.println("in service create user");
        userDao.insert(new User("test"));
    }
}
