package com.inin.dao;

import com.inin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 * Created by root on 12/5/16.
 */
@Repository
public class UserDaoMysqlImpl implements UserDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Insert a user
     * @param user user object
     * @return id of newly created user
     */
    public int insert(User user){
        System.out.println("test dao insert");
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name",      user.getName())
                .addValue("createdDate",   user.getCreatedDate());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update("Insert into users (name, createdDate)"
                        + "VALUES(:name, :createdDate)",
                parameters, keyHolder, new String[]{"id"});

        int newUserId =  keyHolder.getKey().intValue();
        return newUserId;
    }
}
