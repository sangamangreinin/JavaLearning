package com.inin.tms.repositary;

import com.inin.tms.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 3/4/16.
 * This class helps to store the users data into the system.
 */
@Repository
public class UserRepository {
    /**
     * To store the users into the system.
     */
    private Map<String, User> users;

    public UserRepository(){
        users = new HashMap<>();
    }

    /**
     * Save the user in to the system
     * @param user User object
     * @return User object
     */
    public User save(User user) {
        users.put(user.getId(), user);
        return user;
    }

    /**
     * Update a user information in to the system
     * @param user User object to save
     * @return User object after save
     */
    public User update(User user) {
        users.put(user.getId(), user);
        return user;
    }
}
