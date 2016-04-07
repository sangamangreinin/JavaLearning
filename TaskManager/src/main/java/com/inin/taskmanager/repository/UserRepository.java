package com.inin.taskmanager.repository;

import com.inin.taskmanager.domain.User;
import com.inin.taskmanager.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by virendra on 5/4/16.
 * UserRepository class.
 */
@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate template;

    /**
     * saves the user object
     * @param obj User object
     * @return id of the newly generated User in the application
     */
    public Long save(User obj) {
        String query = String.format("INSERT INTO %s (name, address, created, modified) " +
                "VALUES (?,?,?,?)", User.TABLE_NAME);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String created = LocalDateTime.now().format(Util.DB_DATE_FORMAT);
        String modified = LocalDateTime.now().format(Util.DB_DATE_FORMAT);


        template.update(
                con -> {
                    PreparedStatement pst =
                            con.prepareStatement(query, new String[] {"id"});
                    pst.setString(1, obj.getName());
                    pst.setString(2, obj.getAddress());
                    pst.setString(3, created);
                    pst.setString(4, modified);

                    return pst;
                },
                keyHolder);
        Long id = keyHolder.getKey().longValue();
        return id;
        /*User returnObj = null;
        if (id != 0){
            returnObj = find(id.toString());
        }
        return returnObj;*/

    }

    /**
     * finds the user in the application
     * @param userId user id
     * @return User object
     */
    public User find(String userId) {

        String query = String.format("SELECT id AS userId,name,address,created AS createdDate " +
                "FROM %s WHERE id=?", User.TABLE_NAME);



        User user = template.queryForObject(query, new Object[]{userId},
                (entry, i) -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    java.time.LocalDateTime created = LocalDateTime.parse(entry.getString("createdDate"), formatter);

                    return
                            new User.Builder(entry.getString("name"))
                                    .setUserId(entry.getLong("userId"))
                                    .setAddress(entry.getString("address"))
                                    .setCreatedDate(created)
                                    .create();
                });
        return user;
    }

    /**
     * searches the user based on the name passed by the caller
     * @param name String name
     * @return User object
     */
    public User search(String name) {

        String query = String.format("SELECT id AS userId,name,address,created AS createdDate " +
                "FROM %s WHERE name=?", User.TABLE_NAME);

        User user = template.queryForObject(query, new Object[]{name},
                (entry, i) -> {
                    java.time.LocalDateTime created = LocalDateTime.parse(entry.getString("createdDate"), Util.DB_DATE_FORMAT);

                    return
                            new User.Builder(entry.getString("name"))
                                    .setUserId(entry.getLong("userId"))
                                    .setAddress(entry.getString("address"))
                                    .setCreatedDate(created)
                                    .create();
                });
        return user;
    }
}
