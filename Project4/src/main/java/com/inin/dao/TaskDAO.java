package com.inin.dao;

import com.inin.domain.Task;

/**
 * Created by root on 8/4/16.
 */
public interface TaskDAO {

    int insert(Task task);

    Task findById(int id);

    Task update(Task task, int taskId);
}
