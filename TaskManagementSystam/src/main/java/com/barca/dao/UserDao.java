package com.barca.dao;

import com.barca.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * Created by root on 5/4/16.
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(User user)  {
        jdbcTemplate.update("insert into user(name,email,created,modified) values (?,?,?,?)", user.getName(), user.getEmail(), LocalDateTime.now(), LocalDateTime.now());
    }

    public User findUser(long id) throws DataAccessException {
        return jdbcTemplate.queryForObject("select * from user where id = ? ", new Object[]{id}, (resultSet, i) -> {
            return new User(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("email"));
        });
    }

    public boolean isUserIdAlreadyExist(long id) {
        User user = jdbcTemplate.queryForObject("select * from user where id = ? ", new Object[]{id}, (resultSet, i) -> {
            return new User(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("email"));
        });

        if (user == null)
            return false;
        else
            return true;
    }

}
