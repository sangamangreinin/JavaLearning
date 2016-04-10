package com.inin.dao;

import com.inin.domain.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Deepak on 7/4/16.
 * This is dao class for Users in system which will communicate with the db.
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * This method is responsible to store system user data in database table.
     * */
    public int insert(SystemUser systemUser){

        String sqlQuery = "INSERT INTO inin_system_user(name, email, username, password) VALUES(?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,systemUser.getName());
            ps.setString(2,systemUser.getEmail());
            ps.setString(3,systemUser.getUsername());
            ps.setString(4,systemUser.getPassword());
            return ps;

        }, keyHolder);

        int userId = keyHolder.getKey().intValue();

        return userId;

    }


    /**
     * This method is responsible for fetch all the user list from database.
     * */
    public List<SystemUser> getAllUsers() {

        return jdbcTemplate.query("select * from inin_system_user", (resultSet, i) -> {
            return new SystemUser(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("email"),
                    resultSet.getString("username"), resultSet.getString("password"));
        });
    }


    /**
     * This method is responsible for fetch the data of particular user from db.
     * */
    public SystemUser getUser(int uid){
        return jdbcTemplate.queryForObject("SELECT * from inin_system_user where id = ?",
                new Object[] {uid}, (resultSet, i) -> {
                    return new SystemUser(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("email"),
                            resultSet.getString("username"), resultSet.getString("password"));});
    }


    /**
     * This is responsible for updating the user information in database
     * */
    public int updateUser(int uid, SystemUser systemUser){
        String sqlQuery = "UPDATE inin_system_user SET name = ?, username = ?, password = ? WHERE id ="+uid;

        /*KeyHolder keyHolder = new GeneratedKeyHolder();*/

        return jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ps.setString(1,systemUser.getName());
            ps.setString(2,systemUser.getUsername());
            ps.setString(3,systemUser.getPassword());
            return ps;

        });

    }
}




























