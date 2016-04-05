package com.inin.taskmanager.repository;

import com.inin.taskmanager.domain.Task;
import com.inin.taskmanager.utils.Util;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

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


    /**
     * default constructor
     */
    public TaskRepository() {
    }


    public Task save(Task task) {
        String query = String.format("INSERT INTO %s " +
                "(taskid, title,description, created_by, assigned_to, status, end_date, created,modified) " +
                "VALUES (?,?,?,?,?,?,?,?,?)", Task.TABLE_NAME);
        String taskId = getUniqueId();
        int added =
                template.update(
                        query,
                        Util.getMasterTaskId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getCreatedBy(),
                        task.getAssignedTo(),
                        task.getStatus(),
                        task.getEndDate(),
                        task.getCreatedDate(),
                        task.getModifiedDate()
                );
        Task obj = null;

        if (added == 1){
            obj = find(taskId);
        }
        return obj;


    }

    private Task find(String taskId) {
        String query = String.format("SELECT " +
                "id, taskid, title,description, created_by, assigned_to, status, end_date, created,modified) " +
                "FROM %s WHERE taskid=?", Task.TABLE_NAME);



        List<Task> tasks = template.query(query, new Object[]{taskId}, (entry, i) -> {
            return
                    new Task
                            .Builder(entry.getString("title"), entry.getString("description"))
                            .setCreatedBy(userRepository.find(entry.getString("created_by")))
                            .setAssignedTo(userRepository.find(entry.getString("assigned_to")))
                            .setStatus(Util.getTaskStatus(entry.getString("status")))
                            .setEndDate(LocalDateTime.fromDateFields(entry.getDate("end_date")))
                            .setCreatedDate(LocalDateTime.fromDateFields(entry.getDate("end_date")))
                            .setModifiedDate(LocalDateTime.fromDateFields(entry.getDate("end_date")))
                            .create();
        });

        Optional<Task> task = tasks.stream().filter(i->i.getTaskId().equals(taskId)).findAny();

        return task.isPresent() ? task.get() : null;
    }

    private String getUniqueId() {
        return Util.getMasterTaskId();
    }
}
