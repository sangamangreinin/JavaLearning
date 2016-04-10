package com.inin.dao;

import com.inin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Created by root on 10/4/16.
 */
@Repository
public class UserDAOJDBCImpl implements UserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * create user
     *
     * @param user User object
     * @return int id of the newly created User
     */
    @Override
    public int createUser(User user) {

        KeyHolder generateKeyHolder = new GeneratedKeyHolder();
        String query = "insert into users ( name, gender, created) values(?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, String.valueOf(user.getGender()));
            preparedStatement.setObject(3, user.getCreated());
            return preparedStatement;
        }, generateKeyHolder);

        return generateKeyHolder.getKey().intValue();
    }
}
