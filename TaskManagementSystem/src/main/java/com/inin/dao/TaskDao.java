package com.inin.dao;

import com.inin.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by Deepak on 7/4/16.
 * This Dao class for task, responsible for db related task.
 */
@Repository
public class TaskDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserDao userDao;


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * This is responsible for store new Task in Db.
     * @param task
     * @return int is id of newly created task
     * */
    public int insert(Task task){
        String sqlQuery = "INSERT INTO inin_task(assigner_id, assignee_id, title, description, " +
                "current_status, created, due_date, assign_date) VALUES(?,?,?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String createDate = task.getCreated().toString().replace('T', ' ').substring(0, 19);
        String dueDate = task.getDueDate() == null ? null : task.getDueDate().toString().replace('T', ' ').substring(0, 19);
        String assignDate = task.getAssingDate() == null ? null : task.getAssingDate().toString().replace('T', ' ').substring(0, 19);

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, task.getAssigner().getId());
            ps.setInt(2, task.getAssignee().getId());
            ps.setString(3,task.getTitle());
            ps.setString(4,task.getDescription());
            ps.setString(5,task.getCurrentStatus().name());
            ps.setString(6,createDate);
            ps.setString(7,dueDate);
            ps.setString(8,assignDate);
            return ps;

        }, keyHolder);

        int taskId = keyHolder.getKey().intValue();

        return taskId;
    }


    public List<Task> getAll() {
        return jdbcTemplate.query("select * from inin_task", (resultSet, i) -> {
            return new Task(resultSet.getInt("id"), resultSet.getInt("assigner_id"), resultSet.getInt("assignee_id"),
                    resultSet.getString("title"), resultSet.getString("description"),
                    resultSet.getString("current_status"),
                    LocalDateTime.parse(resultSet.getString("created"), formatter),
                    resultSet.getString("assign_date") != null ? LocalDateTime.parse(resultSet.getString("assign_date"), formatter): null,
                    resultSet.getString("due_date") != null ? LocalDateTime.parse(resultSet.getString("due_date"), formatter) : null);
        });
    }


    public int addComments(Comment comment, int tid){
        String sqlQuery = "INSERT INTO inin_comments(task_id, user_id, message, created) VALUES(?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String createDate = comment.getCreated().toString().replace('T', ' ').substring(0, 19);

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, tid);
            ps.setInt(2,comment.getCommenter().getId());
            ps.setString(3, comment.getMessage());
            ps.setString(4, createDate);
            return ps;

        }, keyHolder);

        int commentId = keyHolder.getKey().intValue();

        return commentId;
    }


    public Task get(int id){

        return jdbcTemplate.queryForObject("SELECT * from inin_task where id = ?",
                new Object[] {id}, (resultSet, i) -> {
                    return new Task(resultSet.getInt("id"), resultSet.getInt("assigner_id"), resultSet.getInt("assignee_id"),
                            resultSet.getString("title"), resultSet.getString("description"), resultSet.getString("current_status"),
                            LocalDateTime.parse(resultSet.getString("created"), formatter),
                            resultSet.getString("assign_date") != null ? LocalDateTime.parse(resultSet.getString("assign_date"), formatter): null,
                            resultSet.getString("due_date") != null ? LocalDateTime.parse(resultSet.getString("due_date"), formatter) : null);});
    }


    public int update(int id, Task newTask){

        String dueDate = newTask.getDueDate() == null ? null : newTask.getDueDate().toString().replace('T', ' ').substring(0, 19);

        String assignDate = newTask.getAssingDate() == null ? null : newTask.getAssingDate().toString().replace('T', ' ').substring(0, 19);

        int assigneeId = newTask.getAssigneeId();

        return jdbcTemplate.update("UPDATE inin_task SET assignee_id = ?,  title = ?, " +
                        "description = ?, current_status = ?, due_date = ?, assign_date = ? WHERE  id = ?",
                assigneeId ,newTask.getTitle(),newTask.getDescription(),
                newTask.getCurrentStatus().name(), dueDate,assignDate, id);
    }


    public List<Comment> getAllComments(int tid){
        return jdbcTemplate.query("SELECT * FROM inin_comments WHERE task_id = ?",new Object[]{tid},(rs, rowNum) -> {
            return new Comment(
                    rs.getInt("id"),
                    rs.getInt("task_id"),
                    rs.getInt("user_id"),
                    rs.getString("message"),
                    LocalDateTime.parse(rs.getString("created"), formatter)
            );
        });
    }

}