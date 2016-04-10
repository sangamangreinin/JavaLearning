package com.inin.dao;

import com.inin.controller.dto.QueryRequest;
import com.inin.model.Comment;
import com.inin.model.Task;

import java.util.List;

/**
 * Created by root on 5/4/16.
 */
public interface TaskDAO {
    int createTask(Task task);

    List<Task> query(QueryRequest queryRequest);

    Task findTaskById(int taskId);

    void updateTask(Task task);

    int addComment(Comment comment);

    List<Comment> viewCommentsForTask(int taskId);

    boolean isTaskExists(int taskId);
}
