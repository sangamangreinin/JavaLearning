package com.inin.taskmanagement.dao;

import com.inin.taskmanagement.constant.Gender;
import com.inin.taskmanagement.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * Created by Manish Dubey on 5/4/16.
 * User Repository hold the user specific data access logic
 */
@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Insert user record into database
     * @param user
     * @return return user id if user successfully created into database otherwise return 0
     */
    public long insertUser(User user){
        //Query for inserting user details into database
        String userQuery = "INSERT INTO user(name, address, phone, email,gender, dateOfBirth, createdDate, modifiedDate) " +
                            "values(?, ?, ?, ?, ?, ?, ?, ?)";
        //Insert User into data base
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int row = jdbcTemplate.update((connection) -> {
            PreparedStatement ps = connection.prepareStatement(userQuery.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getAddress());
            ps.setString(3, user.getPhone());
            ps.setString(4,user.getEmail());
            ps.setObject(5,user.getGender().getName());
            ps.setObject(6,user.getDateOfBirth());
            ps.setObject(7, LocalDateTime.now());
            ps.setObject(8, LocalDateTime.now());
            return ps;
        }, keyHolder);

        if(row > 0)
            return keyHolder.getKey().longValue();

        return 0;
    }

    /**
     * Fetch user record by user Id from database
     * @param id
     * @return user by id
     */
    public User getUser(long id){
        return jdbcTemplate.queryForObject("SELECT * FROM user where id = ?",new Object[]{id},(rs, rowNum) -> {
            return new User(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getString("gender").equals("male")?Gender.MALE :Gender.FEMALE,
                    LocalDate.parse(rs.getString("dateOfBirth"), DateTimeFormatter.ISO_DATE),
                    LocalDateTime.parse(rs.getString("createdDate"),DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s")),
                    LocalDateTime.parse(rs.getString("modifiedDate"),DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s"))
            );
        });
    }

    /**
     * Fetch all users record from database
     * @return return list of all user
     */
    public List<User> getUsers(){
        return jdbcTemplate.query("SELECT * FROM user",(rs, rowNum) -> {
            return new User(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getString("gender").equals("male")?Gender.MALE :Gender.FEMALE,
                    LocalDate.parse(rs.getString("dateOfBirth"), DateTimeFormatter.ISO_DATE),
                    LocalDateTime.parse(rs.getString("createdDate"),DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s")),
                    LocalDateTime.parse(rs.getString("modifiedDate"),DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s"))
            );
        });
    }

    /**
     * Delete user from database
     * @param id
     */
    public void deleteUser(long id){
        jdbcTemplate.update("DELETE FROM user WHERE id = ?",new Object[]{id});
    }

    /**
     * Check whether user exist or not
     * @param id
     * @return true if user exist in db otherwise false
     */
    public boolean userExist(long id){
        List<Map<String,Object>> list = jdbcTemplate.queryForList("SELECT count(*) as count FROM user where id = ?",id);
        if((Long)list.get(0).get("count") == 0)
            return false;
        return true;
    }
}
