package com.barca.dao;

import com.barca.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 5/4/16.
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * insert the new user in the Database
     *
     * @param user
     */
    public void insert(User user) {
        jdbcTemplate.update("insert into user(name,email,created,modified) values (?,?,?,?)", user.getName(), user.getEmail(), LocalDateTime.now(), LocalDateTime.now());
    }

    /**
     * fin the user by user Id
     *
     * @param id
     * @return
     */
    public User findUser(long id) {
        return jdbcTemplate.queryForObject("select * from user where id = ? ", new Object[]{id}, (resultSet, i) -> {
            return new User(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("email"));
        });
    }

    /**
     * Check whether user exist or not
     *
     * @param id
     * @return true if user exist in db otherwise false
     */
    public boolean isUserIdAlreadyExist(long id) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT count(*) as count FROM user where id = ?", id);
        if ((Long) list.get(0).get("count") == 0)
            return false;

        return true;
    }

}
