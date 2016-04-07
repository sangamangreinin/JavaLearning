package com.inin.tms.service;

import com.inin.tms.domain.User;
import com.inin.tms.repositary.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by root on 2/4/16.
 * Defines the behaviour of the UserService class
 */
@Service
public class UserService extends BaseService {

    @Autowired
    private UserRepository userRepository;
    /**
     * Creating a new user
     * @param user User Object
     * @return User Object
     */
    public User createUser(User user){
        validate(user.getFirstName(), "firstName");
        validate(user.getEmail(), "email id");
        return userRepository.save(user);
    }

    /**
     *  Update the user
     * @param user User Object
     * @return User Object
     */
    public User updateUser(User user) {
        validate(user.getId(), "User Id");
        return userRepository.update(user);
    }
}
