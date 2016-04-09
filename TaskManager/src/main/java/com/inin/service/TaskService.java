package com.inin.service;

import com.inin.dao.CommentDao;
import com.inin.dao.TaskDao;
import com.inin.domain.Comment;
import com.inin.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by evansbelly on 6/4/16.
 */
@Service
public class TaskService {

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private CommentDao commentDao;

    /**
     * create service for a task
     *
     * @param task
     * @throws IllegalArgumentException if the task object is null
     */
    public int create(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("task can not be empty");
        }
        return taskDao.insert(task);
    }

    /**
     * Service to add a comment to a task
     *
     * @param comment
     * @param taskId
     */
    public void comment(Comment comment, int taskId) {
        commentDao.insert(comment, taskId);
    }

    /**
     * update status of a task
     *
     * @param task
     * @param taskId
     */
    public Task update(Task task, int taskId) {
        Task resultTask = new Task();
        if (task != null) {
            resultTask = taskDao.update(task, taskId);
        }
        return resultTask;
    }

    /**
     * find a task for a given user
     *
     * @param userId
     * @return List of tasks assigned to the user
     */
    public List<Task> findTaskByUser(int userId) {
        return taskDao.findTaskByUser(userId);
    }

    /**
     * find a task by its Id
     * @param taskId
     * @return Task
     */
    public Task findTask(int taskId) {
        return taskDao.findTask(taskId);
    }

    /**
     * search query with status
     * @param search
     * @return List of task with the searched status
     */
    public List<Task> search(String search) {
        return taskDao.search(search);
    }

    public List<Comment> comments(int taskId) {
        return taskDao.comments(taskId);
    }
}
