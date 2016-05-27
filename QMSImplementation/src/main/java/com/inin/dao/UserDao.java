package com.inin.dao;

import com.inin.model.User;

/**
 * Created by root on 12/5/16.
 */

public interface UserDao {
   int insert(User user);
   boolean isUserExist(int id);

}
