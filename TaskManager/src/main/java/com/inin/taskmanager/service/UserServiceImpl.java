package com.inin.taskmanager.service;

import com.inin.taskmanager.domain.User;
import com.inin.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by virendra on 1/4/16.
 * TaskService class.
 * Service class which provides the service to the caller for the tasks.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public Long createUser(User user) throws IllegalAccessException {
        if(user.getName() == null){
            throw new IllegalAccessException("user name is required");
        }
        return userRepository.save(user);
    }

    @Override
    public User find(String userId) {
        return userRepository.find(userId);
    }

    @Override
    public User search(String name) {
        return userRepository.search(name);
    }
}

