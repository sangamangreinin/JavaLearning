package com.inin.service;

/**
 * Created by root on 6/4/16.
 */

import com.inin.dao.CommentDao;
import com.inin.dao.TaskDao;
import com.inin.dao.UserDao;
import com.inin.domain.Comment;
import com.inin.domain.Task;
import com.inin.domain.User;
import com.inin.exceptions.TaskDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class consist of all the service related to task , user and comments
 */
@Service
public class TaskService {

    /**
     * get bean of task dao
     */
    @Autowired
    private TaskDao taskDao;

    /**
     * get bean of user dao
     */
    @Autowired
    private UserDao userDao;

    /**
     * get bean of comment dao
     */
    @Autowired
    private CommentDao  commentDao;

    /**
     * create a new user
     * @param user
     * @return an id of newly created user
     */

    public int createUser(User user){
        if(user == null){
            throw new IllegalArgumentException("User object passed was null");
        }
        return userDao.insert(user);
    }

    /**
     * get user by id
     * @param id
     * @return the user object
     */

    public User findUserById(int id){
        if(id <= 0 ){
            throw new IllegalArgumentException("User Id cannot be 0 or less than 0");
        }
        return userDao.findById(id);
    }

    /**
     * craete a new task
     * @param task
     * @return id of newly created task
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
     * @return
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
     */

    public void addCommentToTask(int taskId, Comment comment){
        if(comment == null){
            throw new IllegalArgumentException("Comment object passed was null");
        }
        commentDao.insert(taskId, comment);
    }

    /**
     * check if task exist or not
     * @param id
     * @return true if exist else false
     */
    public boolean isTaskExist(int id){
         if(!(taskDao.isTaskExist(id))){
             throw new TaskDoesNotExistException("Task with id " +id+" does not exist");
         }
        return true;
    }


    /**
     * get list of comments on a particular task
     */
    public List<Comment> getListOfComments(int taskId){
        return commentDao.findAll(taskId);
    }

    /**
     * update task status and postpone task
     * @param taskId
     * @param task
     * @return
     */
    public void updateTask(int taskId, Task task){
        taskDao.updateTask(taskId, task);
    }
}
