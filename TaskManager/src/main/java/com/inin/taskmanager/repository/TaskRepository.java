package com.inin.taskmanager.repository;

import com.inin.taskmanager.controller.dto.TaskQueryRequest;
import com.inin.taskmanager.domain.Comment;
import com.inin.taskmanager.domain.Task;
import com.inin.taskmanager.domain.User;
import com.inin.taskmanager.exception.RecordNotFoundException;
import com.inin.taskmanager.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by virendra on 1/4/16.
 * TaskRepository class.
 * This class performs the operation of serialization and deserialization of task
 * objects in the file
 */

@Repository
public class TaskRepository {

    /**
     * JDBC connector instance
     */
    @Autowired
    private JdbcTemplate template;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;


    /**
     * default constructor
     */
    public TaskRepository() {
    }


    /**
     * saves the task object
     * @param task Task object
     * @return id of the newly created task
     */
    public Long save(final Task task) {

        String query = String.format("INSERT INTO %s " +
                "(title, description, created_by, assigned_to, status, comments,  end_date, created, modified) " +
                "VALUES (?,?,?,?,?,?,?,?, ?)", Task.TABLE_NAME);

        String created = LocalDateTime.now().format(Util.DB_DATE_FORMAT);
        String modified = LocalDateTime.now().format(Util.DB_DATE_FORMAT);

        String endDate = task.getEndDate() != null ?
                task.getEndDate().format(Util.DB_DATE_FORMAT) : null;

        Long createdBy = task.getCreatedBy().getUserId();
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String commentIds = getCommentIds(task.getComments());

        template.update(
                con -> {
                    PreparedStatement pst =
                            con.prepareStatement(query, new String[] {"id"});


                    Long assignedTo = 0L;

                    if (task.getAssignedTo() != null)
                        assignedTo = task.getAssignedTo().getUserId();

                    pst.setString(1, task.getTitle());
                    pst.setString(2, task.getDescription());
                    pst.setLong(3, createdBy);
                    pst.setLong(4, assignedTo);
                    pst.setString(5, task.getStatus().toString());

                    if (commentIds == null)
                        pst.setNull(6, SqlTypeValue.TYPE_UNKNOWN);
                    else
                        pst.setString(6, commentIds.toString());

                    if (commentIds == null)
                        pst.setNull(7, SqlTypeValue.TYPE_UNKNOWN);
                    else
                        pst.setString(7, endDate);

                    pst.setString(8, created);
                    pst.setString(9, modified);
                    return pst;
                },
                keyHolder);

        Long id = keyHolder.getKey().longValue();

        return id;

    }

    /**
     * gets the comment ids that needs to be stored in the database
     * @param comments Listo g comment objects
     * @return String of comment ids separated by comma
     */
    public String getCommentIds(List<Comment> comments) {
        if (comments == null){
            return null;
        }else {
            StringBuffer buf = new StringBuffer("");
            for (int i = 0;i<comments.size();i++){
                buf.append(comments.get(i).getCommentId()).append(i< comments.size()-1 ? ",":"");
            }
            return buf.toString();
        }
    }

    /**
     * finds the Task object
     * @param taskId String Task Id
     * @return Task object found in the applciation
     */
    public Task find(String taskId) {
        String query = String.format("SELECT " +
                "id, title, description, comments, created_by, assigned_to, status, end_date, created, modified " +
                "FROM %s WHERE id=?", Task.TABLE_NAME);


        List<Task> tasks = template.query(query, new Object[]{taskId}, (entry, i) -> {
            User createdByObj = null;
            User assignedToObj = null;
            try {
                createdByObj = userRepository.find(entry.getString("created_by"));
                assignedToObj = userRepository.find(entry.getString("assigned_to"));
            } catch (DataAccessException e) {

            }


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime endDate = null;
            if (entry.getString("end_date") != null)
                endDate = LocalDateTime.parse(entry.getString("end_date"), formatter);

            LocalDateTime created = LocalDateTime.parse(entry.getString("created"), formatter);
            LocalDateTime modified = LocalDateTime.parse(entry.getString("modified"), formatter);

            return
                    new Task
                            .Builder(entry.getString("title"), entry.getString("description"))
                            .setCreatedBy(createdByObj)
                            .setAssignedTo(assignedToObj)
                            .setStatus(Util.getTaskStatus(entry.getString("status")))
                            .setEndDate(endDate)
                            .setCreatedDate(created)
                            .setModifiedDate(modified)
                            .setTaskId(entry.getLong("id"))
                            .setComments(commentRepository.fetchComments(entry.getString("comments")))
                            .create();
        });

        Optional<Task> task = tasks.stream().filter(i -> i.getTaskId() == Long.parseLong(taskId)).findAny();

        if (task.isPresent()){
            return task.get();
        }else
            throw new RecordNotFoundException("Could not find the task");
    }

    /**
     * finds all the tasks present in the application
     * @return List of tasks
     */
    public List<Task> findAll() {
        String query = String.format("SELECT " +
                "id, title,description, created_by, assigned_to, status, end_date, created, modified " +
                "FROM %s ", Task.TABLE_NAME);


        return template.query(query, (entry, i) -> {
            User createdByObj = null;
            User assignedToObj = null;
            try {
                createdByObj = userRepository.find(entry.getString("created_by"));
                assignedToObj = userRepository.find(entry.getString("assigned_to"));
            } catch (DataAccessException e) {

            }


            LocalDateTime endDate = null;
            if (entry.getString("end_date") != null)
                endDate = LocalDateTime.parse(entry.getString("end_date"), Util.DB_DATE_FORMAT);

            LocalDateTime created = LocalDateTime.parse(entry.getString("created"), Util.DB_DATE_FORMAT);
            LocalDateTime modified = LocalDateTime.parse(entry.getString("modified"), Util.DB_DATE_FORMAT);

            return
                    new Task
                            .Builder(entry.getString("title"), entry.getString("description"))
                            .setCreatedBy(createdByObj)
                            .setAssignedTo(assignedToObj)
                            .setStatus(Util.getTaskStatus(entry.getString("status")))
                            .setEndDate(endDate)
                            .setCreatedDate(created)
                            .setModifiedDate(modified)
                            .setTaskId(entry.getLong("id"))
                            .create();
        });
    }

    /**
     * searches the tasks in the application based on the request made by the caller
     * @param request TaskQueryRequest object
     * @return List of tasks
     */
    public List<Task> search(TaskQueryRequest request) {
        List<Task> list = findAll();
        return list.stream().filter(
                i->i.getCreatedBy().getUserId() == Long.parseLong(request.getCreatorId()))
                .collect(Collectors.toList());
    }

    /**
     * searches the comments for the task
     * @param request TaskQueryRequest object
     * @return List of comment objects
     */
    public List<Comment> searchComments(TaskQueryRequest request) {
        return null;
    }

    /**
     * updates the comment ids in the task entry
     * @param task Task object
     * @param ids ids of comments
     */
    public void updateCommentIds(Task task, String ids) {
        String query = String.format("UPDATE %s " +
                "SET comments=? WHERE id=?", Task.TABLE_NAME);

        template.update(query, new Object[]{ids,task.getTaskId()});

    }
}
