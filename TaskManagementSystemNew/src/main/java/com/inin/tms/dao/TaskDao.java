package com.inin.tms.dao;

import com.inin.tms.domain.Comment;
import com.inin.tms.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by root on 5/4/16.
 * Performing operations on task on DB
 */
@Repository
public class TaskDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Insert new task into the table
     * @param task Task object used to insert task data
     * @return Task id
     */
    public int  save(Task task) {

        String sqlStatement = "insert into tms_task(title,description,status,createdBy,assigned_to,assigned_date,due_date,created,modified) " +
                "values (?,?,?,?,?,?,?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,task.getTitle());
            ps.setString(2,task.getDescription());

            if(task.getStatus().toLowerCase().trim().equals("assigned"))
                ps.setString(3,task.getStatus());
            else
                ps.setString(3, "draft");

            ps.setInt(4,task.getCreatedBy());

            if(task.getStatus().toLowerCase().trim().equals("assigned"))
                ps.setObject(5,LocalDateTime.now());
            else
                ps.setObject(5, "");

            ps.setInt(6,task.getAssignedTo());
            ps.setObject(7, task.getDueDate());
            ps.setObject(8,LocalDateTime.now());
            ps.setObject(9,LocalDateTime.now());
            return ps;
        }, holder);

        return holder.getKey().intValue();
    }

    /**
     * find all tasks
     * @return List of all tasks.
     */
    public List<Task> findAll() {
        List<Task> tasks;
        tasks = jdbcTemplate.query("select * from tms_task", (resultSet, rownum) -> {
            return prepareTask(resultSet);
        });

        if(tasks.isEmpty())
            return null;

        return tasks;
    }

    /**
     * find all tasks of a user
     * @return List of all tasks.
     */
    public List<Task> findAllByIdOrStatus(int id, String status ) {
        List<Task> tasks;
        if(status.isEmpty()) {
            tasks = jdbcTemplate.query("select * from tms_task where createdBy = ?",new Object[]{id}, (resultSet, rownum) -> {
                return prepareTask(resultSet);
            });
        }
        else {
            tasks = jdbcTemplate.query("select * from tms_task where createdBy = ? And status = ?",new Object[]{id,status}, (resultSet, rownum) -> {
                return prepareTask(resultSet);
            });
        }
        if(tasks.isEmpty())
            return null;

        return tasks;
    }

    /**
     * Get a task form the table
     * @param id Unique task id to get the task
     * @return Task Object with task data
     */
    public Task find(int id){
        List<Task> task = jdbcTemplate.query("select * from tms_task where id = ?", new Object[]{id},(resultSet, i) -> {
            return prepareTask(resultSet);
        });

        if (task.isEmpty())
            return null;

        return task.get(0);
    }

    /**
     * Creating a task
     * @param resultSet Reault from table
     * @return Task object
     * @throws SQLException
     */
    private Task prepareTask(ResultSet resultSet) throws SQLException {
        return new Task(
            resultSet.getInt("id"),
            resultSet.getString("title"),
            resultSet.getString("description"),
            resultSet.getString("status"),
            resultSet.getInt("createdBy"),
            resultSet.getInt("assigned_to"),
            LocalDateTime.parse(resultSet.getString("dueDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s")),
            LocalDateTime.parse(resultSet.getString("assignedDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s")),
            LocalDateTime.parse(resultSet.getString("created"), DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s")),
            LocalDateTime.parse(resultSet.getString("modified"), DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s"))
        );
    }

    public int addComment(Comment comment, int taskId, int userId) {
        String sqlStatement = "insert into tms_comment(comment,taskId,userId,created) values (?,?,?,?)";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,comment.getComment());
            ps.setInt(2,taskId);
            ps.setInt(3,userId);
            ps.setObject(4,LocalDateTime.now());
            return ps;
        }, holder);

        return holder.getKey().intValue();
    }

    public List<Comment> getComment(int id) {
        List<Comment> comments = jdbcTemplate.query("select * from tms_comment where taskId = ?",new Object[]{id}, (resultSet, rownum) -> {
            return new Comment(
                resultSet.getInt("id"),
                resultSet.getInt("taskId"),
                resultSet.getString("comment"),
                resultSet.getInt("userId"),
                LocalDateTime.parse(resultSet.getString("created"),DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s"))
            );
        });

        if(comments.isEmpty())
            return null;

        return comments;
    }

    public Task update(Task task, int id) {
        Task existingTask = find(id);
        if(!task.getStatus().isEmpty()){
            existingTask.setStatus(task.getStatus());
            if(task.getStatus().equals("assigned")){
                existingTask.setAssignedDate(LocalDateTime.now());
            }
        }
        if(task.getAssignedTo() > 0) {
            existingTask.setAssignedTo(task.getAssignedTo());
        }
        if(task.getDueDate() != null){
            existingTask.setDueDate(task.getDueDate());
        }

        jdbcTemplate.update("UPDATE tms_task SET status = ?, assigned_to = ?,  assignedDate = ?, dueDate = ?, modified = ? WHERE  id = ?",
                existingTask.getStatus(), existingTask.getAssignedTo(),existingTask.getAssignedDate(), existingTask.getDueDate(),LocalDateTime.now(),id);

        return existingTask;

    }
}