package com.inin.taskmanager.service;

import com.inin.taskmanager.domain.User;
import org.springframework.stereotype.Service;

/**
 * Created by virendra on 1/4/16.
 * TaskService class.
 * Service class which provides the service to the caller for the tasks.
 */
@Service
public interface UserService {

    Long createUser(User user) throws IllegalAccessException;

    User find(String userId);

    User search(String name);
}
