package com.inin.dao;

import com.inin.domain.Task;

import java.util.List;

/**
 * Created by root on 18/4/16.
 */
public interface TaskDao {
    int insert(Task task);

    List<Task> findAllTaskByUserId(int id);

    List<Task> getListOfAllDraftTask();

    boolean isTaskExist(int id);

    void updateTask(int taskId, Task task);
}
