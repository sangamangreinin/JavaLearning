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
import java.util.List;

/**
 * This class deals with all the DB related operations for task
 */
@Repository
public class TaskDao {
    /**
     * get bean of jdbcTemplate for DB connection
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * insert/create a new task
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

    /**
     * find all task for a particular user
     * @param id
     * @return
     */
    public List<Task> findAllTaskByUserId(int id){
        return jdbcTemplate.query("Select * from tasks where assignedId = ?", new Object[] {id}, (resultSet, rownum) -> {
            return new Task(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getInt("taskStatus"),
                    resultSet.getInt("assigneeId"),
                    resultSet.getInt("assignedId"),
                    resultSet.getDate("startDate").toLocalDate(),
                    resultSet.getDate("createdDate").toLocalDate()
            );
        });
    }

    /**
     * find all Draft task
     * @return
     */
    public List<Task> getListOfAllDraftTask(){
        return jdbcTemplate.query("Select * from tasks where assignedId = 0",  (resultSet, rownum) -> {
            return new Task(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getInt("taskStatus"),
                    resultSet.getInt("assigneeId"),
                    resultSet.getInt("assignedId"),
                    resultSet.getDate("startDate").toLocalDate(),
                    resultSet.getDate("createdDate").toLocalDate()
            );
        });
    }

    /**
     * check if the particular task exist or not
     * @param id
     * @return
     */
    public boolean isTaskExist(int id) {
       int count = jdbcTemplate.queryForObject("Select count(*) from tasks where id = ?", new Object[]{id}, Integer.class);
        if(count > 0){
            return true;
        }
        return false;
    }

    /**
     * update task status and postpone date
     * @param taskId
     * @param task
     * @return
     */
    public void updateTask(int taskId, Task task) {
        jdbcTemplate.update("UPDATE tasks SET taskStatus = ?, startDate = ?  WHERE  id = ? ",
                task.getTaskStatus(), task.getTaskStartDate(), taskId);

    }
}
