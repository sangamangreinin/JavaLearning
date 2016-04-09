package com.inin.dao;

import com.inin.domain.User;
import com.inin.exception.OnCreateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Created by root on 6/4/16.
 */
@Repository
public class UserDAO {

    private final String TABLE = "tm_user";

    @Autowired
    private JdbcTemplate template;

    /**
     * creates a new user with db object passed
     * @param user - user db model object
     * @return - last insert Id in the database
     */
    public int insert(User user) {
        String query = "INSERT INTO "+TABLE+ "(name, gender, email, username, password, created, modified) VALUES (?, ?, ?, ?, ?, ?, ?)";
        KeyHolder key = new GeneratedKeyHolder();

        int i = template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getGender()+"");
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getUsername());
            ps.setString(5, user.getPassword());
            ps.setObject(6, user.getCreated());
            ps.setObject(7, user.getModified());

            return ps;
        }, key);

        return key.getKey().intValue();

    }
}
