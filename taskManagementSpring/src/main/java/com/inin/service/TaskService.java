package com.inin.service;

/**
 * Created by root on 6/4/16.
 */

import com.inin.dao.TaskDao;
import com.inin.dao.UserDao;
import com.inin.domain.Task;
import com.inin.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * contains all the functionalities related to task and user
 */
@Service
public class TaskService {

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private UserDao userDao;

    /**
     * create a new user
     * @param user
     * @return an id of newly created user
     */

    public int createUser(User user){
        if(user == null){
            throw new IllegalArgumentException("User object passed was null");
        }
        return userDao.insert(user);
    }

    /**
     * get user by id
     * @param id
     * @return the user object
     */

    public User findUserById(int id){
        if(id <= 0 ){
            throw new IllegalArgumentException("User Id cannot be 0 or less than 0");
        }
        return userDao.findById(id);
    }

    /**
     * craete a new task
     * @param task
     * @return id of newly created task
     */
    public int createTask(Task task){
        if(task == null){
            throw new IllegalArgumentException("Task object passed was null");
        }
        return taskDao.insert(task);
    }

    public List<Task> getListOfTaskByUserId(int id){
        if(id <= 0 ){
            throw new IllegalArgumentException("User Id cannot be 0 or less than 0");
        }
        return taskDao.findAllTaskByUserId(id);
    }
}
