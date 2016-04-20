package com.inin.dao;

/**
 * Created by root on 6/4/16.
 */

import com.inin.Util;
import com.inin.controllers.dto.QueryRequest;
import com.inin.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
public class TaskDaoImpl implements TaskDao{
    /**
     * get bean of jdbcTemplate for DB connection
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * insert/create a new task
     * @param task task object
     * @return the newly generated task id
     */
    public int insert(Task task){
        String sqlStatement = "Insert into tasks (name, description, taskStatus,  assigneeId, assignedId, startDate, createdDate, dueDate) values (?, ?, ?, ?, ?, ?, ?, ?)";
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
            ps.setObject(8, task.getDueDate());

            return ps;
        }, holder);

        int newTaskId = holder.getKey().intValue();

        return newTaskId;
    }

    /**
     * search query to get list of task
     * @return the list of task depending on filter criteria specified
     */
    public List<Task> query(QueryRequest queryRequest){
        //The NamedParameterJdbcTemplate class helps you specify the named parameters instead of classic placeholder(?) argument
        //Named parameters improves readability and are easier to maintain
        //It functionality is similar to JdbcTemplate.
        StringBuilder sql = new StringBuilder("select * from tasks where 1=1 ");
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        if (!Util.isInValidInt(queryRequest.status)) {
            sql.append(" and taskStatus = :taskStatus");
            parameters.addValue("taskStatus", queryRequest.status);
        }
        if (!Util.isInValidInt(queryRequest.assignedId)) {
            sql.append(" and assignedId = :assignedId");
            parameters.addValue("assignedId", queryRequest.assignedId);
        }

        //BeanPropertyRowMapper maps each row of the resultset with a new instance of target class. THe target class should have a no-argument default constructor.
        //It maps the column names from resultset to properties of target class using public setter methods. If column name does not match with property name, we need to provide
        // a column alias matching the property name
        return namedParameterJdbcTemplate.query(sql.toString(), parameters,  new BeanPropertyRowMapper<>(Task.class));
    }

    /**
     * check if the particular task exist or not
     * @param id task id in int
     * @return the true if task exist else false
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
     * @param taskId task id in ints
     * @param task the task object
     */
    public void updateTask(int taskId, Task task) {
        jdbcTemplate.update("UPDATE tasks SET taskStatus = ?, startDate = ? , dueDate = ? WHERE  id = ? ",
                task.getTaskStatus(), task.getTaskStartDate(), task.getDueDate(), taskId);
    }
}
