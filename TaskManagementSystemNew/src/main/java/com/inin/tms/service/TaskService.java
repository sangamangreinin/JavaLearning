package com.inin.tms.service;

import com.inin.tms.dao.TaskDao;
import com.inin.tms.domain.Comment;
import com.inin.tms.domain.Task;
import com.inin.tms.exception.BadRequestException;
import com.inin.tms.exception.ResourceNotFoundException;
import com.inin.tms.repositary.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by root on 2/4/16.
 * Defines the behaviour of the TaskService class
 */
@Service
public class TaskService extends BaseService{

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private TaskValidation taskValidation;

    /**
     * Creating a new task
     * @param task Task object
     * @return Task object
     * @throws IllegalArgumentException if task data is invalid.
     */
    public Task create(Task task, int userId) {
        if(task == null){
            throw  new IllegalArgumentException("Task object passed cant be null.");
        }
        validate(task.getTitle(), "title");
        validate(userId, "userId");
        Task prepareTask = taskValidation.prepareTask(task);
        prepareTask.setCreatedBy(userId);
        int taskId = taskDao.save(prepareTask);
        prepareTask.setId(taskId);
        return prepareTask;
    }

    /**
     * Get atask
     * @param id Task id which is used to get the task details
     * @return Task object
     * @throws ResourceNotFoundException if task mis not present in the system.
     */
    public Task getTask(int id){
        Task task = taskDao.find(id);
        if(task == null)
            throw new ResourceNotFoundException("Task " + id + " is not present in the system");

        return  task;
    }

    /**
     *  Get all tasks (or specific user task's)
     * @return List of all tasks Object
     * @throws ResourceNotFoundException if No tasks are present in the system
     */
    public List<Task> getTasks(int id) {
        List<Task> tasks = taskDao.findAll(id);
        if(tasks == null)
            throw new ResourceNotFoundException("No asks are in the system");

        return tasks;
    }

    /**
     *
     * @param id Unique ticket id
     * @param updateTask Task object for update
     * @return updated Task object
     * @throws ResourceNotFoundException if task is not present in the system.
     * @throws BadRequestException Throws if data given for update is incorrect
     */
    public Task update(String id, Task updateTask) {
        validate(id , " task id ");
        Task task = taskRepository.get(id);
        if (task != null) {
            if(!id.equals(task.getId())) {
                throw new BadRequestException("Bad Request.");
            }

            Class<?> classTicketSource = updateTask.getClass();
            Class<?> classTicketActual = task.getClass();

            Field[] fields = classTicketSource.getDeclaredFields();
            for (Field field : fields) {
                try {
                    Field fieldSource = classTicketSource.getDeclaredField(field.getName());
                    Field fieldDestination = classTicketActual.getDeclaredField(field.getName());

                    Object object = fieldSource.get(updateTask);
                    if(object != null) {
                        fieldDestination.setAccessible(true);
                        fieldDestination.set(task, object);
                    }

                } catch (NoSuchFieldException | IllegalAccessException Nsf) {
                    Nsf.printStackTrace();
                }
            }
            Task updatedTask = taskValidation.prepareTask(task);
            taskRepository.update(updatedTask);
            return updatedTask;
        }
        else{
            throw new ResourceNotFoundException("No Ticket Found.");
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
    public Comment comment(Comment comment, int userId, int taskId) {
        validate(userId , " user id ");
        validate(taskId , " task id ");

        Task task = taskDao.find(taskId);
        if (task != null) {
            validate(comment.getComment(),"task comment");
            comment.setCommentBy(userId);
            comment.setTaskId(taskId);
            int commentId = taskDao.addComment(comment);
            comment.setId(commentId);
            return comment;
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
        validate(id , " task id ");

        Task task = taskDao.find(id);
        if (task != null) {
            return taskDao.getComment(id);
        }
        else{
            throw new ResourceNotFoundException("No Task Found.");
        }
    }
}
