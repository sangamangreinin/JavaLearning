package com.inin.dao;

import com.inin.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;

/**
 * This class deals with all the DB related operations for user
 */
@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * insert a new user
     * @param user
     * @return an id af new created user
     */

    public int insert(User user){
        String sqlStatement = "Insert into users (name, created) values (?, ?)";
        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setObject(2, LocalDateTime.now());
            return ps;
        }, holder);

        int newUserId = holder.getKey().intValue();

        return newUserId;
    }

    /**
     * find the particular user by id
     * @param id
     * @return the user object
     */
    public User findById(int id){
        return jdbcTemplate.queryForObject("Select id, name from users where id = ?", new Object[] {id}, ((resultSet, i) -> {
            return new User(resultSet.getInt("id"), resultSet.getString("name"));
        }));
    }

}
