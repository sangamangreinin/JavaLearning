package com.inin.taskmanager.repository;

import com.inin.taskmanager.domain.User;
import com.inin.taskmanager.utils.Util;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by virendra on 5/4/16.
 * UserRepository class.
 */
@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate template;

    public int save(User obj) {
        String query = String.format("INSERT INTO %s (userid, name,, address, created,modified) " +
                "VALUES (?,?,?,?,?)", User.TABLE_NAME);

        return template.update(
                query,
                Util.getMasterUserId(),
                obj.getName(),
                obj.getAddress(),
                obj.getCreatedDate(),
                obj.getModifiedDate()
        );

    }

    public User find(String userId) {
        String query = String.format("SELECT userid AS userId,name,address,created AS createdDate,modified as modifiedDate) " +
                "FROM %s WHERE userid=?", User.TABLE_NAME);

        User user = template.queryForObject(query, new Object[]{userId},User.class);
        return user;
    }
}
