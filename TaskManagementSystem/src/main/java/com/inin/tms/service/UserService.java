package com.inin.tms.service;

import com.inin.tms.domain.User;
import org.springframework.stereotype.Service;


/**
 * Created by root on 2/4/16.
 * Defines the behaviour of the UserService class
 */
@Service
public class UserService extends BaseService {

    /**
     *
     * @param user
     * @return
     */
    public User createUser(User user){
        validateNotNull(user.getFirstName(), "firstName");

        return null;
    }
}
