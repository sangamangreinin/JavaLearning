package com.inin.service;

import com.inin.controller.dto.CommentRequest;
import com.inin.controller.dto.QueryRequest;
import com.inin.controller.dto.TaskRequest;
import com.inin.dao.TaskDAO;
import com.inin.helper.Util;
import com.inin.model.Comment;
import com.inin.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Created by root on 6/4/16.
 */
@Service
public class TaskService {

    @Autowired
    private TaskDAO taskDAO;

    /**
     * create a task
     *
     * @param taskRequest TaskRequest DTO
     * @return int newly created id of the task
     */
    public int createTask(TaskRequest taskRequest) {
        String title = taskRequest.title;
        String description = taskRequest.description;
        String status = taskRequest.status.toString();
        int assigner = taskRequest.assigner;
        int assignee = taskRequest.assignee;
        LocalDateTime startTime = null;
        LocalDateTime dueTime = null;
        try {
            startTime = Util.parseStringToLocalDateTime(taskRequest.startTime);
            dueTime = Util.parseStringToLocalDateTime(taskRequest.dueTime);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid start time or due time provided");
        }

        Task task = new Task(title, description, status, assigner, assignee, startTime, dueTime);
        return taskDAO.createTask(task);
    }

    /**
     * get task
     *
     * @param taskId id of the task
     * @return Task
     */
    public Task getTask(int taskId) {
        if (taskId <= 0) {
            throw new IllegalArgumentException("Task id cannot be zero or negative");
        }
        return taskDAO.findTaskById(taskId);
    }

    /**
     * update task based on task id
     *
     * @param taskId      id of the Task which needs to be updated
     * @param taskRequest DTO object
     */
    public void updateTask(int taskId, TaskRequest taskRequest) {
        Task task = taskDAO.findTaskById(taskId);
        LocalDateTime dueTime = Util.parseStringToLocalDateTime(taskRequest.dueTime);
        String status = taskRequest.status.toString();
        task.setDueTime(dueTime);
        task.setStatus(status);
        taskDAO.updateTask(task);
    }

    /**
     * add comment on task
     *
     * @param taskId
     * @param commentRequest
     * @return
     */
    public int addComment(int taskId, CommentRequest commentRequest) {
        if (!taskDAO.isTaskExists(taskId)) {
            throw new IllegalArgumentException("Task Id " + taskId + " not found");
        }

        String comment = commentRequest.comment;
        int userId = commentRequest.userId;

        Comment objComment = new Comment(taskId, comment, userId);
        return taskDAO.addComment(objComment);
    }

    /**
     * view comments for Task
     *
     * @param taskId
     * @return
     */
    public List<Comment> getComments(int taskId) {
        if (!taskDAO.isTaskExists(taskId)) {
            throw new IllegalArgumentException("Task Id " + taskId + " not found");
        }
        return taskDAO.viewCommentsForTask(taskId);
    }

    /**
     * view Task based on search criteria
     *
     * @param userId
     * @param queryRequest
     * @return
     */
    public List<Task> search(int userId, QueryRequest queryRequest) {
        return taskDAO.query(queryRequest);
    }
}
