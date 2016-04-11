package com.barca.service;

import com.barca.constant.Constant;
import com.barca.dao.CommentDao;
import com.barca.dao.TaskDao;
import com.barca.dao.UserDao;
import com.barca.exception.TaskNotFound;
import com.barca.exception.UserNotFoundException;
import com.barca.model.Comment;
import com.barca.model.Task;
import com.barca.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by root on 7/4/16.
 */
@Service
public class TaskService {

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private UserDao userDao;

    /**
     * @param userId
     * @param task
     * @return the task Id to the caller
     */
    public long createTask(long userId, Task task) {

        checkUser(userId);
        validationTask(task);
        setTaskStatus(task);
        return taskDao.insert(userId, task);
    }

    /**
     * check user is Exist or not
     *
     * @param userId
     * @throws IllegalArgumentException
     * @throws UserNotFoundException
     */
    private void checkUser(long userId) throws IllegalArgumentException, UserNotFoundException {

        if (userId < 0)
            throw new IllegalArgumentException("userId Id should be valid");

        if (!userDao.isUserIdAlreadyExist(userId))
            throw new UserNotFoundException("User Not Found");
    }

    /**
     * set the proper status for status
     *
     * @param task
     * @throws IllegalArgumentException if status is wrong
     */
    private void setTaskStatus(Task task) throws IllegalArgumentException {
        if (task.getStatus().equalsIgnoreCase("draft")) {
            task.setStatus(Constant.DRAFT);
        } else if (task.getStatus().equalsIgnoreCase("assigned")) {
            task.setStatus(Constant.ASSIGNED);
        } else if (task.getStatus().equalsIgnoreCase("completed")) {
            task.setStatus(Constant.COMPLETED);
        } else {
            throw new IllegalArgumentException("Status should be valid [draft,assigned,completed]");
        }
    }

    /**
     * task object fields validation
     *
     * @param task
     * @throws IllegalArgumentException if task object or subject status Assignee is not send proper
     */
    private void validationTask(Task task) throws IllegalArgumentException {
        if (task == null)
            throw new IllegalArgumentException("Task can't be null");

        if (Util.isInValidString(task.getSubject()))
            throw new IllegalArgumentException("Subject should be null or empty");

        if (Util.isInValidString(task.getStatus()))
            throw new IllegalArgumentException("status should be null or empty");

        if (Util.isInValidString(task.getStatus()))
            throw new IllegalArgumentException("status should be null or empty");

        if (task.getAssigneeId() < 0)
            throw new IllegalArgumentException("Assignee Id should be valid");

    }

    /**
     * add comment in particular task
     *
     * @param taskId
     * @param comment
     * @return
     * @throws IllegalArgumentException if task Id is not send proper
     */

    public ResponseEntity addComment(long taskId, Comment comment) {

        checkTask(taskId);

        commentDao.addComment(taskId, comment);
        return new ResponseEntity(HttpStatus.CREATED);
    }


    /**
     * get Task by task Id
     *
     * @param taskId
     * @return task object
     * @throws IllegalArgumentException if task Id is not send proper
     */
    public Task getTask(long taskId) throws IllegalArgumentException {
        checkTask(taskId);
        return taskDao.getTask(taskId);
    }

    /**
     * get Tasks by userId or Status  is optional
     *
     * @param userId
     * @param status
     * @return
     */
    public List<Task> getTasks(long userId, String status) {

        checkUser(userId);

        return taskDao.getTasks(userId, status);
    }

    /**
     * update the Task Object as per the valid field provided with  request Payload
     *
     * @param userId
     * @param taskId
     * @param task
     * @return the task Object
     */
    public Task updateTask(long userId, long taskId, Task task) {

        checkUser(userId);

        checkTask(taskId);

        Task oldTask = getTask(taskId);

        if (!Util.isInValidString(task.getSubject())) {
            oldTask.setSubject(task.getSubject());
        }

        if (!Util.isInValidString(task.getStatus())) {
            setTaskStatus(task);
            oldTask.setStatus(task.getStatus());
        }

        if (!Util.isInValidString(task.getDescription())) {
            oldTask.setDescription(task.getDescription());
        }

        if (!task.getDueDate().equals(null)) {
            oldTask.setDueDate(task.getDueDate());
        }

        return taskDao.updateTask(userId, taskId, oldTask);
    }

    /**
     * check the task in system exist or not
     *
     * @param taskId
     * @throws TaskNotFound             if the Task not Exist in the system
     * @throws IllegalArgumentException if id is not Proper
     */
    private void checkTask(long taskId) throws TaskNotFound, IllegalArgumentException {
        if (taskId < 0)
            throw new IllegalArgumentException("taskId Id should be valid");

        if (!taskDao.isTaskExist(taskId))
            throw new TaskNotFound("Task Not Found");

    }

    /**
     * get All comments for particular task by task Id
     *
     * @param taskId
     * @return list of comment
     */
    public List<Comment> getComments(long taskId) throws IllegalArgumentException {
        checkTask(taskId);
        return commentDao.getComments(taskId);

    }
}
