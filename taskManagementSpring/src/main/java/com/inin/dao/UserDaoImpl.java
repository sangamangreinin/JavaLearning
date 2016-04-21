package com.inin.dao;

import com.inin.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


/**
 * This class consist of all the DB related operations for user
 */
@Repository
public class UserDaoImpl implements UserDao{
    /**
     * get bean of jdbcTemplate for DB connection
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    /**
     * insert a new user
     * @param user
     * @return an id af new created user
     */

    public int insert(User user){
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name",           user.getName())
                .addValue("created",        user.getCreatedDate());


        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update("Insert into users (name, created)"
                        + "VALUES(:name, :created)",
                parameters, keyHolder, new String[]{"id"});

        int newUserId =  keyHolder.getKey().intValue();
        return newUserId;
    }

    /**
     * find the particular user by id
     * @param id
     * @return the user object
     */
    public User findById(int id){
        String sql = "Select id, name, created from users where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] {id}, new BeanPropertyRowMapper<>(User.class));
    }

}
