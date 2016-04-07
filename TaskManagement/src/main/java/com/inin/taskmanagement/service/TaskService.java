package com.inin.taskmanagement.service;

import com.inin.taskmanagement.constant.TaskStatus;
import com.inin.taskmanagement.dao.TaskDao;
import com.inin.taskmanagement.domain.Comment;
import com.inin.taskmanagement.domain.Task;
import com.inin.taskmanagement.exception.NotAuthorizedException;
import com.inin.taskmanagement.exception.ResourceCreationException;
import com.inin.taskmanagement.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Manish Dubey on 5/4/16.
 * Task service class, perform business logic of task and interacting with task dao for db operation
 */
@Service
public class TaskService {
    /**
     * Task dao interacting with db
     */
    @Autowired
    private TaskDao taskDao;

    @Autowired
    private UserService userService;

    /**
     * Create new task
     * @param task
     */
    public void create(Task task){
        validateTaskData(task);
        long id = taskDao.create(task);
        if(id <= 0)
            throw new ResourceCreationException("Failed to create task");
    }

    /**
     * Validate task data
     * @param task
     */
    private void validateTaskData(Task task) {
        if(task == null)
            throw new IllegalArgumentException("Task can't be null");
        if(!Util.isValidString(task.getName()))
            throw new IllegalArgumentException("Task's name must be required");
        if(task.getTaskAssignerId() <= 0 || !userService.userExist(task.getTaskAssignerId()))
            throw new IllegalArgumentException("Invalid task assigner ID");

        if(!Util.isValidString(task.getStatus().getValue())) {
            throw new IllegalArgumentException("Invalid task status");
        }
        if(task.getStatus().getValue().equals("assigned")) {
            if (task.getTaskDoerId() <= 0 || !userService.userExist(task.getTaskDoerId()))
                throw new IllegalArgumentException("Invalid task doer ID");
            if(task.getDueDate() == null){
                throw new IllegalArgumentException("Due date cannot be null");
            }
        }
    }

    /**
     * Get the task by Id
     * @param id
     * @return task if valid id provided
     * @throws IllegalArgumentException if invalid id is provided
     */
    public Task getTask(long id){
        if(id <= 0)
            throw new IllegalArgumentException("Id must be numeric and greater than 0");
        try{
            return taskDao.getTask(id);
        }catch (DataAccessException e)
        {
            throw new IllegalArgumentException("Invalid Id provided. No task associated with id ");
        }
    }

    /**
     * Update task
     * @param id
     * @param task
     * @return Updated task details
     */
    public Task update(long id, Task task){
        String updateBy = "";
        if(id <= 0)
            throw new IllegalArgumentException("Id must be numeric and greater than 0");

        if(task == null)
            throw new IllegalArgumentException("Task can't be null");
        if(task.getTaskAssignerId() > 0) {
            if (!userService.userExist(task.getTaskAssignerId()))
                throw new NotAuthorizedException("You are not allowed perform this operation task ");

            if (!Util.isValidString(task.getStatus().getValue()) && task.getTaskDoerId() <= 0 && task.getDueDate() == null)
                throw new IllegalArgumentException("At least one of parameters status, taskDoerId or dueDate  required for perform task update ");

            if(Util.isValidString(task.getStatus().getValue()) && !TaskStatus.isValidTaskStatus(task.getStatus().getValue()))
                throw new IllegalArgumentException("Invalid Status is provided");

            if(task.getTaskDoerId() > 0 && !userService.userExist(task.getTaskDoerId()))
                throw new NotAuthorizedException("Invalid Task Doer Id");

            updateBy = "taskAssigner";
        }else if(task.getTaskAssignerId() <= 0 && task.getTaskDoerId() > 0 && task.getDueDate() != null){
            if (!userService.userExist(task.getTaskDoerId()))
                throw new NotAuthorizedException("You are not allowed perform this operation task ");
            updateBy = "taskDoer";
        }

        try {
            return taskDao.update(id, task,updateBy);
        }catch (DataAccessException  e){

            throw new IllegalArgumentException("Invalid Task is provided :"+e.getMessage());
        }

    }

    /**
     * Validate comment data
     * @param taskId
     * @param comment
     */
    public void comments(long taskId, Comment comment){
        if(taskId <= 0)
            throw new IllegalArgumentException("Task Id must be numeric and greater than 0");
        if(comment == null)
            throw new IllegalArgumentException("Comment can't be null");
        if(comment.getUserId() <= 0 || !userService.userExist(comment.getUserId()))
            throw new NotAuthorizedException("You are not allowed on this task with"+taskId);
        //Validate commented is valid or not
        isValidTaskUser(taskId, comment.getUserId());
        long id = taskDao.comments(taskId,comment);
        if(id <= 0)
            throw new ResourceCreationException("Failed to comment on task");
    }

    /**
     * Validate is user is task assigner or task doer
     * @param taskId
     * @param userId
     */
    public void isValidTaskUser(long taskId, long userId){
        try{
            Task task = getTask(taskId);
            if(userId != task.getTaskAssignerId() && userId != task.getTaskDoerId())
                throw new NotAuthorizedException("You are not allowed to comments on this task with"+taskId);
        }catch (DataAccessException  e){
            throw new IllegalArgumentException("Invalid task Id provided");
        }
    }

    /**
     * Validate task id and get the all comments of task
     * @param taskId
     * @return list of comments
     */
    public List<Comment> getComments(long taskId,long userId){
        if(taskId <= 0)
            throw new IllegalArgumentException("Task Id must be numeric and greater than 0");

        if(userId <= 0)
            throw new IllegalArgumentException("Task Assigner Id must be numeric and greater than 0");
        Task task;
        try{
            task = taskDao.getTask(taskId);
        }catch (DataAccessException e ){
            throw new IllegalArgumentException("Invalid task Id is provided");
        }

        if(userId != task.getTaskAssignerId() && userId != task.getTaskDoerId())
            throw new NotAuthorizedException("User not allowed view the comments of task");

        return taskDao.getComments(taskId);

    }

    public List<Task> getTasks(long userId, String status){

        if(userId <= 0 || !userService.userExist(userId))
            throw new NotAuthorizedException("Invalid user Id is provided");
        if(Util.isValidString(status) && !TaskStatus.isValidTaskStatus(status))
            throw new IllegalArgumentException("Invalid status provided");
        return taskDao.getTasks(userId,status);
    }

}
