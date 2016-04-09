package com.inin.dao;

import com.inin.domain.Comment;
import com.inin.domain.Task;
import com.inin.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by evansbelly on 6/4/16.
 */
@Repository
public class TaskDao {

    private LocalDateTime now = LocalDateTime.now();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * insert query for task
     * @param task
     * @return the task id after successful insertion
     */
    public int insert(Task task) {

        String sqlStatement = "insert into task(title, status, description, assigner, assignedTo, endDate, created, modified) values (?,?,?,?,?,?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, task.getTitle());
            ps.setString(2, task.getStatus());
            ps.setString(3, task.getDescription());
            ps.setInt(4, task.getAssigner());
            ps.setInt(5, task.getAssignedTo());
            ps.setObject(6, task.getEndDate());
            ps.setObject(7, task.getCreated());
            ps.setObject(8, task.getModified());
            return ps;
        }, holder);

        return holder.getKey().intValue();
    }

    /**
     * find task for the user given
     * @param userId
     * @return List of task for the specified user
     */
    public List<Task> findTaskByUser(int userId) {
        return jdbcTemplate.query("select * from task where assignedTo = ?",
                new Object[]{userId}, (resultSet, i) -> {
                    return new Task(resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("status"),
                            resultSet.getString("description"),
                            resultSet.getInt("assigner"),
                            resultSet.getInt("assignedTo"),
                            (Util.localDateFromSqlTimestamp((Timestamp) resultSet.getObject("modified"))),
                            (Util.localDateFromSqlTimestamp((Timestamp) resultSet.getObject("created"))),
                            (Util.localDateFromSqlTimestamp((Timestamp) resultSet.getObject("endDate"))));
                });
    }

    /**
     * update status of the task
     * postpone the task
     * @param task
     * @param taskId
     */
    public Task update(Task task, int taskId) {
        String updateStatement = "";
        String status = task.getStatus();
        if (status != null) {
            if (task.getAssignedTo() != 0) {
                updateStatement = " UPDATE task "
                        + " SET status=?, modified=?, endDate=?, assignedTo=? "
                        + " WHERE id=? ";
                jdbcTemplate.update(updateStatement, new Object[]{status, now, task.getEndDate(), task.getAssignedTo(), taskId});
            } else {
                updateStatement = " UPDATE task "
                        + " SET status=?, modified=? "
                        + " WHERE id=? ";
                jdbcTemplate.update(updateStatement, new Object[]{status, now, taskId});
            }
            return findTask(taskId);
        } else if (task.getEndDate() != null) {
            updateStatement = " UPDATE task "
                    + " SET endDate=?, modified=?"
                    + " WHERE id=? ";
            jdbcTemplate.update(updateStatement, new Object[]{task.getEndDate(), now, taskId});
            return findTask(taskId);
        } else {
            throw new IllegalArgumentException("Incorrect data");
        }
    }

    /**
     * find a task by Id
     * @param taskId
     * @return Task object for the specified task id
     */
    public Task findTask(int taskId) {
        return jdbcTemplate.queryForObject("select * from task where id = ?",
                new Object[]{taskId}, (resultSet, i) -> {
                    return new Task(resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("status"),
                            resultSet.getString("description"),
                            resultSet.getInt("assigner"),
                            resultSet.getInt("assignedTo"),
                            (Util.localDateFromSqlTimestamp((Timestamp) resultSet.getObject("modified"))),
                            (Util.localDateFromSqlTimestamp((Timestamp) resultSet.getObject("created"))),
                            (Util.localDateFromSqlTimestamp((Timestamp) resultSet.getObject("endDate"))));
                });

    }

    /**
     * search query for status
     * @param search
     * @return List of task
     */
    public List<Task> search(String search) {
        return jdbcTemplate.query("select * from task where status = ?",
                new Object[]{search}, (resultSet, i) -> {
                    return new Task(resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("status"),
                            resultSet.getString("description"),
                            resultSet.getInt("assigner"),
                            resultSet.getInt("assignedTo"),
                            (Util.localDateFromSqlTimestamp((Timestamp) resultSet.getObject("modified"))),
                            (Util.localDateFromSqlTimestamp((Timestamp) resultSet.getObject("created"))),
                            (Util.localDateFromSqlTimestamp((Timestamp) resultSet.getObject("endDate"))));
                });

    }

    /**
     * get the list of comments for a task
     * @param taskId
     * @return List of comments
     */
    public List<Comment> comments(int taskId) {
        return jdbcTemplate.query("select * from comment where taskId = ?",
                new Object[]{taskId}, (resultSet, i) -> {
                    return new Comment(resultSet.getInt("id"),
                            resultSet.getString("comment"),
                            resultSet.getInt("commentBy"),
                            (Util.localDateFromSqlTimestamp((Timestamp) resultSet.getObject("modified"))),
                            (Util.localDateFromSqlTimestamp((Timestamp) resultSet.getObject("created"))));
                });
    }
}
