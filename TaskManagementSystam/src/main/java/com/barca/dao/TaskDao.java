package com.barca.dao;

import com.barca.model.Task;
import com.barca.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;


/**
 * Created by root on 7/4/16.
 */
@Repository

public class TaskDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * insert the new task in the database
     *
     * @param id
     * @param task
     * @return the auto generated task Id by the database for the task
     */
    public long insert(long id, Task task) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        // performs the insert in the database
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(
                                "insert into task(subject,description,status,assigneeId,assignerId,dueDate,created,modified) values (?,?,?,?,?,?,?,?)",
                                new String[]{"id"});

                        int index = 1;
                        ps.setString(index++, task.getSubject());
                        ps.setString(index++, task.getDescription());
                        ps.setString(index++, task.getStatus());
                        ps.setLong(index++, task.getAssigneeId());
                        ps.setLong(index++, id);
                        ps.setObject(index++, task.getDueDate());
                        ps.setObject(index++, LocalDateTime.now());
                        ps.setObject(index++, LocalDateTime.now());
                        return ps;
                    }
                },
                keyHolder);

        // retrieves the value primary key for the inserted key
        return (long) keyHolder.getKey();
    }


    /**
     * get task by Id for the Database
     *
     * @param id
     * @return Task Object
     */
    public Task getTask(long id) {
        return jdbcTemplate.queryForObject("select * from task where id = ? ", new Object[]{id}, (resultSet, i) -> {
            return new Task(resultSet.getLong("id"),
                    resultSet.getString("subject"),
                    resultSet.getString("description"),
                    resultSet.getString("status"),
                    resultSet.getLong("assigneeId"),
                    resultSet.getLong("assignerId"),
                    LocalDateTime.parse(resultSet.getString("dueDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s.S")),
                    LocalDateTime.parse(resultSet.getString("created"), DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s.S")),
                    LocalDateTime.parse(resultSet.getString("modified"), DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s.S"))
            );
        });

    }

    /**
     * get Tasks list by user Id and Status ,status field is Optional
     *
     * @param userId
     * @param status
     * @return List of Tasks
     */
    public List<Task> getTasks(long userId, String status) {
        if (Util.isInValidString(status)) {
            return jdbcTemplate.query("SELECT * FROM task WHERE assignerId = ?", new Object[]{userId}, (resultSet, rowNum) -> {
                return new Task(resultSet.getLong("id"),
                        resultSet.getString("subject"),
                        resultSet.getString("description"),
                        resultSet.getString("status"),
                        resultSet.getLong("assigneeId"),
                        resultSet.getLong("assignerId"),
                        LocalDateTime.parse(resultSet.getString("dueDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s.S")),
                        LocalDateTime.parse(resultSet.getString("created"), DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s.S")),
                        LocalDateTime.parse(resultSet.getString("modified"), DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s.S"))
                );
            });
        } else {
            return jdbcTemplate.query("SELECT * FROM task WHERE assignerId = ? AND status = ?", new Object[]{userId, status}, (resultSet, rowNum) -> {
                return new Task(resultSet.getLong("id"),
                        resultSet.getString("subject"),
                        resultSet.getString("description"),
                        resultSet.getString("status"),
                        resultSet.getLong("assigneeId"),
                        resultSet.getLong("assignerId"),
                        LocalDateTime.parse(resultSet.getString("dueDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s.S")),
                        LocalDateTime.parse(resultSet.getString("created"), DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s.S")),
                        LocalDateTime.parse(resultSet.getString("modified"), DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s.S"))
                );
            });
        }


    }

    /**
     * update task
     *
     * @param userId
     * @param taskId
     * @param task
     * @return return the updated task
     */

    public Task updateTask(long userId, long taskId, Task task) {
        jdbcTemplate.update("UPDATE task SET subject = ?, description = ?,  status = ?, assigneeId = ?, assignerId = ? , dueDate = ? , modified = ? WHERE  id = ? ",
                task.getSubject(), task.getDescription(), task.getStatus(), task.getAssigneeId(), task.getAssignerId(), task.getDueDate(), LocalDateTime.now(), taskId);
        return task;
    }


    /**
     * Check whether task exist or not
     *
     * @param id
     * @return true if user exist in db otherwise false
     */
    public boolean isTaskExist(long id) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT count(*) as count FROM task where id = ?", id);
        if ((Long) list.get(0).get("count") == 0)
            return false;
        return true;
    }


}
