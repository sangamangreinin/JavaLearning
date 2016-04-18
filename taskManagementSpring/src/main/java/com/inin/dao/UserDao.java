package com.inin.dao;

import com.inin.domain.User;

/**
 * Created by root on 18/4/16.
 */
public interface UserDao {
    int insert(User user);

    User findById(int id);
}
