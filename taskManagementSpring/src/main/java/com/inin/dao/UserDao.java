package com.inin.dao;

import com.inin.model.User;

/**
 * Created by root on 18/4/16.
 */
public interface UserDao {
    int insert(User user);

    User findById(int id);
}
