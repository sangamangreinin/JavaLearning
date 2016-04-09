package com.inin.dao;

import com.inin.domain.Comment;
import com.inin.domain.Task;
import com.inin.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by root on 6/4/16.
 */
@Repository
@Component("taskDAOImpl")
public class TaskDAOImpl implements TaskDAO {

    private final String TASK = "tm_task";
    private final String COMMENT = "tm_comment";

    @Autowired
    private JdbcTemplate template;

    /**
     * creates a Task
     * @param task - task model object to insert
     * @return Last Inserted Id in the database
     */
    @Override
    public int insert(Task task){
        String query = "INSERT INTO "+TASK+ "(description, status, assignee, assigned_to, due_by , created, modified) VALUES (?, ?, ?, ?, ?, ?, ?)";
        KeyHolder key = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1 , task.getDescription());
            ps.setString(2 , task.getStatus());
            ps.setInt(3 , task.getAssignee());
            ps.setInt(4 , task.getAssignedTo());
            ps.setObject(5 , task.getDueDate());
            ps.setObject(6 , task.getCreated());
            ps.setObject(7 , task.getModified());
            return ps;
        },key);

        return key.getKey().intValue();
    }

    /**
     * Gets the Task by Id
     * @param id - integer id of the Task
     * @return Task Object
     */
    @Override
    public Task findById(int id){
        String sql = "SELECT * from "+TASK+"  where id = ?";
        return template.queryForObject(sql,new Object[] {id}, (resultSet, i) ->{
            return getTask(resultSet);
        });
    }

    /**
     *
     * @param resultSet - set of rows found from the database
     * @return Task object found
     * @throws SQLException
     */
    private Task getTask(ResultSet resultSet) throws SQLException {
        Task t = new Task(
            resultSet.getString("description"),
            resultSet.getString("status"),
            resultSet.getInt("assignee"),
            resultSet.getInt("assigned_to"),
            Util.parseSqlDateToLocalDate((Timestamp) resultSet.getObject("due_by")));
            t.setId(resultSet.getInt("id"));
            t.setCreated(Util.parseSqlDateToLocalDate((Timestamp) resultSet.getObject("created")));
            t.setModified(Util.parseSqlDateToLocalDate((Timestamp) resultSet.getObject("modified")));
        return t;
    }

    /**
     *
     * @param task - task model object to update
     * @param taskId - task id
     * @return task model object
     */
    @Override
    public Task update(Task task, int taskId){
        int res = template.update("UPDATE "+TASK+" set due_by = ? , status = ?, modified = ? WHERE id= ? ",
                task.getDueDate(),
                task.getStatus(),
                LocalDateTime.now(),
                taskId
                );

        if (res > 0){
            return task;
        }

        return null;
    }

    /**
     *
     * @param status - string status passed that is Draft
     * @param assignee - integer value of user assigned to the tasks/s
     * @return List of Task object found
     */
    public List<Task> findAllByDraft(String status, int assignee){
        String sql = "SELECT * from "+TASK+"  where assignee = ? and status = ?";

        return template.query(sql, new Object[]{assignee,status}, (resultSet, rowNum) -> {
            return getTask(resultSet);
        });
    }

    /**
     * fetches all comments for that task id
     * @param id - task id
     * @return
     */
    public List<Comment> getAllComments(int id) {
        String sql = "SELECT * from "+COMMENT+"  where taskId = ?";

        return template.query(sql, new Object[]{id}, (resultSet, rowNum) -> {
            Comment c = new Comment(
                    resultSet.getString("comment"),
                    resultSet.getInt("taskId"),
                    resultSet.getInt("userId"));
                c.setCreated(Util.parseSqlDateToLocalDate((Timestamp) resultSet.getObject("created")));
            return c;
        });
    }
}
