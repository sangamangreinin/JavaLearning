package com.inin.service;

/**
 * Created by root on 6/4/16.
 */

import com.inin.Util;
import com.inin.controllers.dto.CommentRequest;
import com.inin.controllers.dto.QueryRequest;
import com.inin.controllers.dto.TaskRequest;
import com.inin.dao.CommentDao;
import com.inin.dao.TaskDao;
import com.inin.model.Comment;
import com.inin.model.Task;
import com.inin.exceptions.TaskDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class consist of all the service related to task  and comments
 */
@Service
public class TaskService {

    /**
     * get bean of task dao
     */
    @Autowired
    private TaskDao taskDao;

    /**
     * get bean of comment dao
     */
    @Autowired
    private CommentDao commentDao;

    /**
     * create a new task
     * @param taskRequest
     * @return id of newly created task
     * @throws IllegalArgumentException if the task object passed is null
     */
    public int createTask(TaskRequest taskRequest){
        validateTask(taskRequest);

        Task task = new Task(taskRequest.name, taskRequest.description, taskRequest.status, taskRequest.assignorId,
                taskRequest.assigneeId, taskRequest.startDate, taskRequest.dueDate);
        return taskDao.insert(task);
    }


    /**
     * get list of  task depending on search criteria
     */
    public List<Task> query(QueryRequest queryRequest){
        return taskDao.query(queryRequest);
    }

    /**
     * add comment to task
     * @param taskId task id in int
     * @param commentRequest comment request object
     * @throws IllegalArgumentException if the comment object passed was null or invalid argument
     */

    public int addCommentToTask(int taskId, CommentRequest commentRequest){
        validateComment(commentRequest);
        isTaskExist(taskId);
        Comment comment = new Comment(commentRequest.description, commentRequest.commentedBy);
        return commentDao.insert(taskId, comment);
    }

    /**
     * check if task exist or not
     * @param id task id in int
     * @return true if exist else false
     * @throws TaskDoesNotExistException if the specified task id does not exist
     */
    public boolean isTaskExist(int id){
         if(!(taskDao.isTaskExist(id))){
             throw new TaskDoesNotExistException("Task with id " +id+" does not exist");
         }
        return true;
    }

    /**
     * get list of comments on a particular task
     * @param taskId task id in int
     * @return the list of comments
     * @throws TaskDoesNotExistException if the specified task id does not exist
     */
    public List<Comment> getListOfComments(int taskId){
        isTaskExist(taskId);
        return commentDao.findAll(taskId);
    }

    /**
     * update task status and postpone task
     * @param taskId task id in int
     * @param taskRequest task request object
     * @throws TaskDoesNotExistException if the specified task id does not exist
     */
    public void updateTask(int taskId, TaskRequest taskRequest){
        isTaskExist(taskId);
        Task task = taskDao.findById(taskId);

        task.setDueDate(taskRequest.dueDate);
        task.setStartDate(taskRequest.startDate);
        task.setTaskStatus(taskRequest.status);

        taskDao.updateTask(taskId, task);
    }

    /**
     * validate task attributes
     * @param taskRequest task request object
     */
    private void validateTask(TaskRequest taskRequest){
        if(taskRequest == null){
            throw new IllegalArgumentException("Task Request object passed was null");
        }
        if(Util.isInValidString(taskRequest.name)){
            throw new IllegalArgumentException("name, may not be empty");
        }
        if(Util.isInValidString(taskRequest.description)){
            throw new IllegalArgumentException("description, may not be empty");
        }
        if(taskRequest.status != 1){
            if(Util.isInValidInt(taskRequest.assigneeId)){
                throw new IllegalArgumentException("error assigned to required , assigned to  may not be 0 or less than 0");
            }
        }
        if(Util.isInValidInt(taskRequest.assignorId)){
            throw new IllegalArgumentException("error assignorId required , assignor to  may not be 0 or less than 0");
        }
        if(taskRequest.status <= 0 || taskRequest.status > 4){
            throw new IllegalArgumentException("Invalid task status, should be either 1, 2 , 3 , 4 or cannot be zero");
        }
    }

    /**
     * validate comment attributes
     * @param commentRequest comment request object
     */
    private void validateComment(CommentRequest commentRequest){
        if(commentRequest == null){
            throw new IllegalArgumentException("Comment Request object passed was null");
        }
        if(Util.isInValidString(commentRequest.description)){
            throw new IllegalArgumentException("Comment description attribute cannot be null or value is missing");
        }
        if(Util.isInValidInt(commentRequest.commentedBy)){
            throw new IllegalArgumentException("Invalid commentedBy id");
        }
    }
}
