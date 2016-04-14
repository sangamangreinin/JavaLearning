package com.inin.dao;

/**
 * Created by root on 6/4/16.
 */

import com.inin.domain.Task;
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

/**
 * This class deals with all the DB related operations for task
 */
@Repository
public class TaskDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * insert a new task
     * @param task
     */
    public int insert(Task task){
        String sqlStatement = "Insert into tasks (name, description, taskStatus,  assigneeId, assignedId, startDate, createdDate) values (?, ?, ?, ?, ?, ?, ?)";
        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, task.getTaskName());
            ps.setString(2, task.getTaskDescription());
            ps.setInt(3,    task.getTaskStatus());
            ps.setInt(4,    task.getAssignee());
            ps.setInt(5,    task.getAssignedTo());
            ps.setObject(6, task.getTaskStartDate());
            ps.setObject(7, task.getTaskCreatedDate());

            return ps;
        }, holder);

        int newTaskId = holder.getKey().intValue();

        return newTaskId;
    }

    public List<Task> findAllTaskByUserId(int id){
        System.out.println("in function");
        return jdbcTemplate.query("Select * from tasks where assignedId = ?", new Object[] {id}, (resultSet, rownum) -> {
            return new Task(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getInt("taskStatus"),
                    resultSet.getInt("assigneeId"),
                    resultSet.getInt("assignedId"),
                    resultSet.getObject("startDate"),
            );
        });
    }


}
