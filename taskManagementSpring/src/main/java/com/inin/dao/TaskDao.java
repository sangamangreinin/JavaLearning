package com.inin.dao;

import com.inin.controllers.dto.QueryRequest;
import com.inin.domain.Task;

import java.util.List;

/**
 * Created by root on 18/4/16.
 */
public interface TaskDao {
    int insert(Task task);

    List<Task> query(QueryRequest queryRequest);

    boolean isTaskExist(int id);

    void updateTask(int taskId, Task task);
}
