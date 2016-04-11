package com.inin.tms.dao;

import com.inin.tms.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;

/**
 * Created by root on 11/4/16.
 *
 */
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Creating a new user into the system
     * @param user User object
     * @return User id
     */
    public int  save(User user) {

        String sqlStatement = "insert into tms_user(first_name,last_name,gender,emailId,created,modified) " +
                "values (?,?,?,?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,user.getFirstName());
            ps.setString(2,user.getLastName());
            ps.setString(3,user.getGender());
            ps.setString(4,user.getEmail());
            ps.setObject(5,LocalDateTime.now());
            ps.setObject(6,LocalDateTime.now());
            return ps;
        }, holder);

        return holder.getKey().intValue();
    }
}
