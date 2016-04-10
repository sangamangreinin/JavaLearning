package com.inin.dao;

import com.inin.controller.dto.QueryRequest;
import com.inin.helper.Util;
import com.inin.model.Comment;
import com.inin.model.Task;
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
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by root on 6/4/16.
 */
@Repository
public class TaskDAOJDBCImpl implements TaskDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * create task
     *
     * @param task Task to be created
     * @return int id of the created Task
     */
    @Override
    public int createTask(Task task) {

        KeyHolder generateKeyHolder = new GeneratedKeyHolder();
        String query = "insert into tasks ( title, description, status, assigner, assignee, startTime, dueTime, created, modified) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setString(3, task.getStatus());
            preparedStatement.setInt(4, task.getAssigner());
            preparedStatement.setInt(5, task.getAssignee());
            preparedStatement.setObject(6, task.getStartTime());
            preparedStatement.setObject(7, task.getDueTime());
            preparedStatement.setObject(8, task.getCreated());
            preparedStatement.setObject(9, task.getModified());
            return preparedStatement;
        }, generateKeyHolder);

        return generateKeyHolder.getKey().intValue();
    }

    /**
     * perform query operations based on query request
     *
     * @param queryRequest
     * @return List<Task> the task result in List
     */
    @Override
    public List<Task> query(QueryRequest queryRequest) {
        StringBuilder query = new StringBuilder("select * from tasks where 1=1 ");
        MapSqlParameterSource params = new MapSqlParameterSource();

        if (Util.isValidString(queryRequest.assigner)) {
            query.append(" and assigner = :assigner");
            params.addValue("assigner", queryRequest.assigner);
        }
        if (Util.isValidString(queryRequest.status.toString())) {
            query.append(" and status = :status");
            params.addValue("status", queryRequest.status);
        }
        return namedParameterJdbcTemplate.query(query.toString(), params, new BeanPropertyRowMapper<>(Task.class));
    }

    /**
     * find task based on id
     *
     * @param taskId id of the task to search
     * @return Task the Task result based on the search
     */
    @Override
    public Task findTaskById(int taskId) {
        String query = "select * from tasks where id = ?";
        return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Task.class), taskId);
    }

    /**
     * update task
     *
     * @param task Task
     */
    @Override
    public void updateTask(Task task) {

        String query = "update tasks set status = ?, dueTime = ?, modified = ? where id = ?";

        jdbcTemplate.update(query, task.getStatus(), task.getDueTime(), LocalDateTime.now(), task.getId());
    }

    /**
     * add comment on task
     *
     * @param comment
     * @return
     */
    @Override
    public int addComment(Comment comment) {

        KeyHolder generateKeyHolder = new GeneratedKeyHolder();
        String query = "insert into comments ( taskId, comment, userId, created) values(?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, comment.getTaskId());
            preparedStatement.setString(2, comment.getComment());
            preparedStatement.setInt(3, comment.getUserId());
            preparedStatement.setObject(4, comment.getCreated());
            return preparedStatement;
        }, generateKeyHolder);

        return generateKeyHolder.getKey().intValue();
    }

    /**
     * view comments for task
     *
     * @param taskId id of the task
     * @return List<Comment> list of comments
     */
    @Override
    public List<Comment> viewCommentsForTask(int taskId) {
        String query = "select * from comments where taskId = ?";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Comment.class), taskId);
    }

    @Override
    public boolean isTaskExists(int taskId) {
        String query = "select count(id) from tasks where id = ?";
        Integer cnt = jdbcTemplate.queryForObject(query, Integer.class, taskId);
        return cnt != null && cnt > 0;
    }
}