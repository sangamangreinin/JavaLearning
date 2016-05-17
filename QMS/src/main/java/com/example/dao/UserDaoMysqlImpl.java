package com.example.dao;

import com.example.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by root on 12/5/16.
 */
@Repository
public class UserDaoMysqlImpl implements UserDao{

    public void insert(User user){
        System.out.println("test dao insert");
    }
}
