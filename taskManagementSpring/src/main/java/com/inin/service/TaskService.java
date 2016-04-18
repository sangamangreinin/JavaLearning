package com.inin.service;

/**
 * Created by root on 6/4/16.
 */

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
     * @param task
     * @return id of newly created task
     * @throws IllegalArgumentException if the task object passed is null
     */
    public int createTask(Task task){
        if(task == null){
            throw new IllegalArgumentException("Task object passed was null");
        }
        return taskDao.insert(task);
    }

    /**
     * get list of task for a particular user
     * @param id
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
     * @param comment
     * @throws IllegalArgumentException if the comment object passed was null
     */

    public void addCommentToTask(int taskId, Comment comment){
        if(comment == null){
            throw new IllegalArgumentException("Comment object passed was null");
        }
        isTaskExist(taskId);
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
     * @param taskId
     * @return the list of comments
     */
    public List<Comment> getListOfComments(int taskId){
        isTaskExist(taskId);
        return commentDao.findAll(taskId);
    }

    /**
     * update task status and postpone task
     * @param taskId
     * @param task
     */
    public void updateTask(int taskId, Task task){
        isTaskExist(taskId);
        taskDao.updateTask(taskId, task);
    }
}
