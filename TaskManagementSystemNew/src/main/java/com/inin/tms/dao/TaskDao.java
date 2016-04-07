package com.inin.tms.dao;

import com.inin.tms.domain.Comment;
import com.inin.tms.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by root on 5/4/16.
 * Performing operations on task
 */
@Repository
public class TaskDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Insert new task into the table
     * @param task Task object used to insert task data
     */
    public int  save(Task task) {

        String sqlStatement = "insert into tms_task(title,description,status,createdBy,assigned_to,due_date,created,modified) values (?,?,?,?,?,?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,task.getTitle());
            ps.setString(2,task.getDescription());
            ps.setString(3,task.getStatus());
            ps.setInt(4,task.getCreatedBy());
            ps.setInt(5,task.getAssignedTo());
            ps.setString(6, String.valueOf(LocalDateTime.now()));
            ps.setString(7,String.valueOf(LocalDateTime.now()));
            ps.setString(8,LocalDateTime.now().toString());
            return ps;
        }, holder);

        return holder.getKey().intValue();
    }

    /**
     * find all tasks (or specific user task's)
     * @return List of all tasks.
     */
    public List<Task> findAll(int id ) {
        System.out.println("inside");
        List<Task> tasks;
        if(id > 0){
            System.out.println("if");
            tasks = jdbcTemplate.query("select * from tms_task where createdBy = ?",new Object[]{id}, (resultSet, rownum) -> {
                return new Task(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("description"), resultSet.getString("status")
                        , resultSet.getInt("createdBy"), resultSet.getInt("assigned_to"));
            });
        }
        else {
            System.out.println("else");
            tasks = jdbcTemplate.query("select * from tms_task", (resultSet, rownum) -> {
                return new Task(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("description"), resultSet.getString("status")
                        , resultSet.getInt("createdBy"), resultSet.getInt("assigned_to"));
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
            return new Task(resultSet.getInt("id"),resultSet.getString("title"), resultSet.getString("description"),resultSet.getString("status"),
                            resultSet.getInt("createdBy"),resultSet.getInt("assigned_to"));
        });
        if (task.isEmpty())
            return null;

        return task.get(0);
    }

    public int addComment(Comment comment) {
        String sqlStatement = "insert into tms_comment(comment,taskId,userId,created) values (?,?,?,?)";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,comment.getComment());
            ps.setInt(2,comment.getTaskId());
            ps.setInt(3,comment.getCommentBy());
            ps.setString(4,LocalDateTime.now().toString());
            return ps;
        }, holder);

        return holder.getKey().intValue();
    }

    public List<Comment> getComment(int id) {
        List<Comment> comments = jdbcTemplate.query("select * from tms_comment where taskId = ?",new Object[]{id}, (resultSet, rownum) -> {
            return new Comment(resultSet.getInt("id"), resultSet.getInt("taskId"), resultSet.getString("comment"), resultSet.getInt("userId"));
        });

        if(comments.isEmpty())
            return null;

        return comments;
    }
}