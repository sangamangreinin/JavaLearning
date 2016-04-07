package com.inin.taskmanagement.service;

import com.inin.taskmanagement.dao.UserDao;
import com.inin.taskmanagement.domain.User;
import com.inin.taskmanagement.exception.ResourceCreationException;
import com.inin.taskmanagement.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Manish Dubey on 5/4/16.
 * User Service hold the user specific business logic
 */
@Service
public class UserService {
    /**
     * UserDao, perform user specific query in database
     */
    @Autowired
    private UserDao userDao;
    /**
     * Validate passed user data and create new user in the system
     * @param user
     * @return created user id
     */
    public void createUser(User user){
        validateUserDto(user);
        long userId = userDao.insertUser(user);
        if(userId == 0)
            throw new ResourceCreationException("User creation failed");
    }

    /**
     * Validate the user request data
     * @param user
     * @return boolean if request body having valid data
     */
    private void validateUserDto(User user){
        if(user == null)
            throw new IllegalArgumentException("Request body can't be empty");
        if(!Util.isValidString(user.getName()))
            throw new IllegalArgumentException("User name must be required");
        if(!Util.isValidString(user.getAddress()))
            throw new IllegalArgumentException("User address must be required");
        if(!Util.isValidString(user.getGender().getName())) {
            throw new IllegalArgumentException("User gender must be required");
        }
        List<String> gender = Arrays.asList("male","female","unknown");
        if(!gender.contains(user.getGender().getName())){
            throw new IllegalArgumentException("Invalid User gender is provided");
        }
        if(!Util.isValidString(user.getPhone()))
            throw new IllegalArgumentException("User phone number must be required");
        if(!Util.isValidString(user.getEmail()))
            throw new IllegalArgumentException("User email must be required");
        if(user.getDateOfBirth() == null)
            throw new IllegalArgumentException("User dob must be required");
    }

    /**
     * Validate user id and fetch user record for database by id
     * @param id
     * @return User object if valid id is provided
     * @throws IllegalArgumentException if id invalid id is provided
     */
    public User getUser(long id){
        if(id <= 0)
            throw new IllegalArgumentException("Valid Id should be required");
        try{
            return userDao.getUser(id);
        }catch (DataAccessException e){
            throw new IllegalArgumentException("No user record found with id :"+id);
        }
    }

    /**
     * Check existence of user
     * @param id
     * @return true if user present with passed id in db
     */
    public boolean userExist(long id){
        return userDao.userExist(id);
    }

    /**
     * Reture the list of user
     * @return user list
     */
    public List<User> getUsers(){
        return userDao.getUsers();
    }


}
