package com.barca.dao;

import com.barca.model.Comment;
import com.barca.model.Task;
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

/**
 * Created by root on 7/4/16.
 */
@Repository
public class TaskDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public long insert(long id, Task task) {
        // jdbcTemplate.update("insert into task(subject,description,status,assigneeId,assignerId,dueDate,created,modified) values (?,?,?,?,?,?,?,?)", task.getSubject(), task.getDescription(), task.getStatus(), task.getAssigneeId(), task.getAssignerId(), task.getDueDate(), LocalDateTime.now(), LocalDateTime.now(), task.getId());

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

    public void addComment(long taskId, Comment comment) {
        jdbcTemplate.update("insert into comment(userId,taskId,comment,created) values (?,?,?,?)", comment.getUserId(), taskId, comment.getComment(), LocalDateTime.now());
    }


    public Task getTask(long id) {
        return jdbcTemplate.queryForObject("select * from task where id = ? ", new Object[]{id}, (resultSet, i) -> {
            return new Task(resultSet.getLong("id"),
                    resultSet.getString("subject"),
                    resultSet.getString("description"),
                    resultSet.getString("status"),
                    resultSet.getLong("assigneeId"),
                    resultSet.getLong("assignerId"),
                    (LocalDateTime) resultSet.getObject("dueDate"),
                    (LocalDateTime)  resultSet.getObject("created"),
                    (LocalDateTime)  resultSet.getObject("modified"));
        });

    }




}
