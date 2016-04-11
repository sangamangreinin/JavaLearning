package com.inin.tms.service;

import com.inin.tms.dao.TaskDao;
import com.inin.tms.domain.Comment;
import com.inin.tms.domain.Task;
import com.inin.tms.exception.BadRequestException;
import com.inin.tms.exception.ResourceCreationFailedException;
import com.inin.tms.exception.ResourceNotFoundException;
import com.inin.tms.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * Created by root on 2/4/16.
 * Defines the behaviour of the TaskService class
 */
@Service
public class TaskService extends BaseService{
    @Autowired
    private TaskDao taskDao;

    /**
     * Creating a new task
     * @param task Task object
     * @return Task object
     * @throws IllegalArgumentException if task data is invalid.
     */
    public int create(Task task, int userId) {
        if(task == null){
            throw  new IllegalArgumentException("Task object passed can't be null.");
        }
        validate(task.getTitle(), "title");
        validate(userId, "userId");
        int id = taskDao.save(task);

        if(id == 0)
            throw  new ResourceCreationFailedException("Task Creation failed due to internal server error");

        return id;
    }

    /**
     * Get atask
     * @param id Task id which is used to get the task details
     * @return Task object
     * @throws ResourceNotFoundException if task mis not present in the system.
     */
    public Task getTask(int id){
        if(id<=0)
            throw new IllegalArgumentException("Task id is invalid, it must be integer number.");

        Task task = taskDao.find(id);

        if(task == null)
            throw new ResourceNotFoundException("Task " + id + " is not present in the system");

        return  task;
    }

    /**
     * Get all tasks
     * @return List of all task objects
     */
    public List<Task> getTasks() {
        List<Task> tasks = taskDao.findAll();

        if(tasks == null)
            throw new ResourceNotFoundException("No asks are in the system");

        return tasks;
    }


    /**
     *  Get all tasks (or specific user task's)
     * @param id User id to get all tasks
     * @return List of all tasks Object
     * @throws ResourceNotFoundException if No tasks are present in the system
     */
    public List<Task> getTasksByIdOrStatus(int id, String status) {
        if(id <= 0)
            throw  new IllegalArgumentException("user id is invalid, it must be integer number.");

        if(!isValidString(status) && !Util.isValidTaskStatus(status)){
            throw new InvalidParameterException("Invalid task status");
        }

        List<Task> tasks = taskDao.findAllByIdOrStatus(id, status);

        if(tasks == null)
            throw new ResourceNotFoundException("No asks are in the system");

        return tasks;
    }

    /**
     * @param task Task object to update
     * @param id Unique ticket id
     * @return updated Task object
     * @throws ResourceNotFoundException if task is not present in the system.
     * @throws BadRequestException Throws if data given for update is incorrect
     */
    public Task update(Task task, int id) {
        if(id <= 0)
            throw  new IllegalArgumentException("task id is invalid, it must be integer number.");

        if (task == null){
            throw  new IllegalArgumentException("Task object passed can't be null.");
        }

        if (taskDao.find(id) != null) {
            if (!isValidString(task.getStatus()) && task.getAssignedTo() <= 0 && task.getDueDate() == null)
                throw new IllegalArgumentException("At least one of parameters status, taskDoerId or dueDate  required to update the task");
            if(!task.getStatus().isEmpty()){
                if(!Util.isValidTaskStatus(task.getStatus())){
                    throw new InvalidParameterException("Invalid task status"); //
                }
            }
            return taskDao.update(task, id);
        }
        else{
            throw new ResourceNotFoundException("You are updating task but that is not found in the system.");
        }
    }

    /**
     * Adding comment on the task in the system
     * @param comment Comment object to add into the task
     * @param userId User id which is going to add comment on the task
     * @param taskId Task id to add comment on the task
     * @return Task object after adding comment
     * @throws BadRequestException if comment data is invalid
     * @throws ResourceNotFoundException if provided task id is not present in the system
     */
    public int comment(Comment comment, int userId, int taskId) {
        if(comment == null){
            throw  new IllegalArgumentException("Task object passed can't be null.");
        }

        if(userId <= 0 || taskId <= 0)
            throw  new IllegalArgumentException("user or task id is invalid, they must be integer number.");

        Task task = taskDao.find(taskId);
        if (task != null) {
            validate(comment.getComment(),"task comment");
            return taskDao.addComment(comment, userId, taskId);
        }
        else{
            throw new ResourceNotFoundException("No Task Found.");
        }

    }

    /**
     * Get all comments on the task from the system
     * @param id Task id to get all comments
     * @return List of all comments
     * @throws ResourceNotFoundException if provided task id is not present in the system
     */
    public List<Comment> getComments(int id) {
        if(id <= 0)
            throw  new IllegalArgumentException("task id is invalid, it must be integer number.");

        Task task = taskDao.find(id);
        if (task != null) {
            return taskDao.getComment(id);
        }
        else{
            throw new ResourceNotFoundException("No Comments are found on the Task " + id);
        }
    }
}
