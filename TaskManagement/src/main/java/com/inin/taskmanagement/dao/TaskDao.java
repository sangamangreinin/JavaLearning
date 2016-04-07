package com.inin.taskmanagement.dao;

import com.inin.taskmanagement.constant.TaskStatus;
import com.inin.taskmanagement.domain.Comment;
import com.inin.taskmanagement.domain.Task;
import com.inin.taskmanagement.exception.NotAuthorizedException;
import com.inin.taskmanagement.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * Created by Manish Dubey on 5/4/16.
 * Task DAO class perform all task related db class
 */
@Repository
public class TaskDao {
    /**
     * Spring jdbc template performing query into database
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public long create(Task task){
        //Query for inserting user details into database
        String userQuery = "INSERT INTO task(name, taskAssignerId, taskDoerId, status, assignDate, dueDate, createdDate, modifiedDate) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?)";
        //Insert User into data base
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int row = jdbcTemplate.update((connection) -> {
            PreparedStatement ps = connection.prepareStatement(userQuery.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, task.getName());
            ps.setLong(2, task.getTaskAssignerId());
            ps.setLong(3, task.getTaskDoerId());
            ps.setString(4,task.getStatus().getValue());
            if(task.getStatus().getValue().equals("assigned"))
                ps.setObject(5, LocalDate.now());
            else
                ps.setString(5,"");
            ps.setObject(6,task.getDueDate());
            ps.setObject(7, LocalDateTime.now());
            ps.setObject(8, LocalDateTime.now());
            return ps;
        }, keyHolder);

        if(row > 0) {
            //Log event into task event
            logTaskEvent(keyHolder.getKey().longValue(),task.getTaskAssignerId(), "Task created by user "+task.getTaskAssignerId());
            return keyHolder.getKey().longValue();
        }
        return 0;
    }

    /**
     * Log happening event into db
     * @param taskId
     * @param userId
     * @param description
     */
    public void logTaskEvent(long taskId, long userId, String description){
        jdbcTemplate.update("INSERT INTO task_log(taskId, userId, description, createdDate, modifiedDate) VALUES (?, ?, ?, ?, ?)",
                taskId,userId,description,LocalDateTime.now(),LocalDateTime.now());
    }

    /**
     * Get task by id
     * @param id
     * @return task valid id provided
     */
    public Task getTask(long id){
        return jdbcTemplate.queryForObject("SELECT * FROM task where id = ?",new Object[]{id},(rs, rowNum) -> {
            return buildTask(rs);
        });
    }

    /**
     * Check task exist in db by passed id
     * @param id
     * @return true if task exist in db other wise false
     */
    public boolean taskExist(long id){
        List<Map<String,Object>> list = jdbcTemplate.queryForList("SELECT count(*) as count FROM task where id = ?",id);
        if((Long)list.get(0).get("count") == 0)
            return false;
        return true;
    }

    /**
     * Update task into database
     * @param id
     * @param task
     * @return
     */
    public Task update(long id, Task task, String updateBy){
        String logMessage = "Task updated with ";
        Task oldTask = getTask(id);
        if(!task.getStatus().getValue().isEmpty()) {
            oldTask.setStatus(task.getStatus());
            logMessage+= "status = "+task.getStatus().getValue()+", ";
        }
        if(task.getStatus().getValue().equals("assigned")) {
            oldTask.setAssignDate(LocalDate.now());
            logMessage+= "assignDate = "+LocalDate.now()+", ";
        }
        if(task.getTaskDoerId() > 0){
            oldTask.setTaskDoerId(task.getTaskDoerId());
            logMessage+= "taskDoerId = "+task.getTaskDoerId()+", ";
        }
        if(task.getDueDate() != null) {
            oldTask.setDueDate(task.getDueDate());
            logMessage+= "dueDate = "+task.getDueDate();
        }
        if(updateBy.equals("taskAssigner"))
            jdbcTemplate.update("UPDATE task SET status = ?, taskDoerId = ?,  assignDate = ?, dueDate = ?, modifiedDate = ? WHERE  id = ? AND taskAssignerId = ?",
                oldTask.getStatus().getValue(),oldTask.getTaskDoerId(),oldTask.getAssignDate(),oldTask.getDueDate(),LocalDateTime.now(),id,task.getTaskAssignerId());
        else if(updateBy.equals("taskDoer"))
            jdbcTemplate.update("UPDATE task SET dueDate = ?, modifiedDate = ? WHERE  id = ? AND taskDoerId = ?",
                    oldTask.getDueDate(),LocalDateTime.now(),id,task.getTaskDoerId());
        //Log the task event
        logTaskEvent(oldTask.getId(),oldTask.getTaskAssignerId(),logMessage);
        return oldTask;
    }

    /**
     * Add comment on task into database
     * @param taskId
     * @param comment
     * @return comment id  if successfully add comment otherwise 0
     */
    public long comments(long taskId, Comment comment){
        //Query for inserting comment details into database
        String commentQuery = "INSERT INTO comment(taskId, userId, comment, createdDate, modifiedDate) " +
                "values(?, ?, ?, ?, ?)";
        //Insert Comment into data base
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int row = jdbcTemplate.update((connection) -> {
            PreparedStatement ps = connection.prepareStatement(commentQuery.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, taskId);
            ps.setLong(2, comment.getUserId());
            ps.setString(3, comment.getComment());
            ps.setObject(4, LocalDateTime.now());
            ps.setObject(5, LocalDateTime.now());
            return ps;
        }, keyHolder);

        if(row > 0) {
            //Log event into task event
            logTaskEvent(taskId,comment.getUserId(), "Comment on task : "+comment.getComment());
            return keyHolder.getKey().longValue();
        }
        return 0;
    }

    /**
     * Fetch the list of comments of task from database
     * @param taskId
     * @return list of comments
     */
    public List<Comment> getComments(long taskId){
        return jdbcTemplate.query("SELECT * FROM comment WHERE taskId = ?",new Object[]{taskId},(rs, rowNum) -> {
            return new Comment(
                    rs.getLong("id"),
                    rs.getLong("taskId"),
                    rs.getLong("userId"),
                    rs.getString("comment"),
                    LocalDateTime.parse(rs.getString("createdDate"),DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s")),
                    LocalDateTime.parse(rs.getString("modifiedDate"),DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s"))
            );
        });
    }

    /**
     * Return the list of task
     * @param userId
     * @param status
     * @return task list
     */
    public List<Task> getTasks(long userId, String status){
        if(!Util.isValidString(status))
            return jdbcTemplate.query("SELECT * FROM task WHERE taskAssignerId = ?", new Object[]{userId}, (rs, rowNum) -> {
                return buildTask(rs);
            });
        else
            return jdbcTemplate.query("SELECT * FROM task WHERE taskAssignerId = ? AND status = ?",new Object[]{userId,status},(rs, rowNum) -> {
                return buildTask(rs);
            });
    }

    /**
     * Created new task from task ResultSet
     * @param rs
     * @return Task
     * @throws SQLException
     */
    private Task buildTask(ResultSet rs) throws SQLException {
        return new Task(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getLong("taskAssignerId"),
                rs.getLong("taskDoerId"),
                TaskStatus.DRAFT.getValue(rs.getString("status")),
                rs.getString("assignDate").isEmpty()? null: LocalDate.parse(rs.getString("assignDate")),
                rs.getString("dueDate").isEmpty() ? null :LocalDate.parse(rs.getString("dueDate")),
                LocalDateTime.parse(rs.getString("createdDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s")),
                LocalDateTime.parse(rs.getString("modifiedDate"),DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s"))
        );
    }


}
