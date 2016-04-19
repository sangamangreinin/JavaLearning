package com.inin.service;

/**
 * Created by root on 6/4/16.
 */

import com.inin.controllers.dto.CommentRequest;
import com.inin.controllers.dto.TaskRequest;
import com.inin.dao.CommentDao;
import com.inin.dao.TaskDao;
import com.inin.domain.Comment;
import com.inin.domain.Task;
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
     * craete a new task
     * @param taskRequest
     * @return id of newly created task
     * @throws IllegalArgumentException if the task object passed is null
     */
    public int createTask(TaskRequest taskRequest){
        if(taskRequest == null){
            throw new IllegalArgumentException("Task object passed was null");
        }
        if(taskRequest.name == null || taskRequest.name == ""){
            throw new IllegalArgumentException("Name attribute cannot be null or  the value is missing ");
        }
        if(taskRequest.description == null || taskRequest.description == ""){
            throw new IllegalArgumentException("Description attribute cannot be null or  the value is missing ");
        }
        //if possible change this to builder pattern later
        Task task = new Task(taskRequest.name, taskRequest.description, taskRequest.status, taskRequest.assignee, taskRequest.assignedTo, taskRequest.startDate, taskRequest.dueDate);
        return taskDao.insert(task);
    }

    /**
     * get list of task for a particular user
     * @param id id if the user in int
     * @return the list of task assigned to a particular user
     * @throws IllegalArgumentException if the user id passed is negative or 0
     */
    public List<Task> getListOfTaskByUserId(int id){
        if(id <= 0 ){
            throw new IllegalArgumentException("User Id cannot be 0 or less than 0");
        }
        return taskDao.findAllTaskByUserId(id);
    }

    /**
     * get list of Draft task
     */
    public List<Task> getListOfAllDraftTask(){
        return taskDao.getListOfAllDraftTask();
    }

    /**
     * add comment to task
     * @param taskId
     * @param commentRequest
     * @throws IllegalArgumentException if the comment object passed was null
     */

    public void addCommentToTask(int taskId, CommentRequest commentRequest){
        if(commentRequest == null){
            throw new IllegalArgumentException("Comment object passed was null");
        }
        if(commentRequest.description == null || commentRequest.description == ""){
            throw new IllegalArgumentException("Comment description attribute cannot be null or value is missing");
        }
        isTaskExist(taskId);
        Comment comment = new Comment(commentRequest.description, commentRequest.commentedBy);
        commentDao.insert(taskId, comment);
    }

    /**
     * check if task exist or not
     * @param id
     * @return true if exist else false
     * @throws TaskDoesNotExistException if the specified does not exist
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
     */
    public List<Comment> getListOfComments(int taskId){
        isTaskExist(taskId);
        return commentDao.findAll(taskId);
    }

    /**
     * update task status and postpone task
     * @param taskId task id in int
     * @param taskRequest
     */
    public void updateTask(int taskId, TaskRequest taskRequest){
        if(taskRequest == null){
            throw new IllegalArgumentException("Task object passed was null");
        }
        isTaskExist(taskId);
        Task task = new Task(taskRequest.name, taskRequest.description, taskRequest.status, taskRequest.assignee, taskRequest.assignedTo, taskRequest.startDate, taskRequest.dueDate);
        taskDao.updateTask(taskId, task);
    }
}
