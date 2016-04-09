package com.inin.dao;

import com.inin.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by evansbelly on 6/4/16.
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(User user) {
        jdbcTemplate.update("insert into user(name, email, created, modified) values (?,?,?,?)", user.getName(), user.getEmail(), user.getCreated(), user.getModified());
    }
}
