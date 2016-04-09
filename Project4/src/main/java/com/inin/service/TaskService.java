package com.inin.service;

import com.inin.dao.CommentDAO;
import com.inin.dao.TaskDAOImpl;
import com.inin.domain.Comment;
import com.inin.domain.Task;
import com.inin.dto.TaskDTO;
import com.inin.exception.OnCreateException;
import com.inin.exception.UserNotAuthorizedException;
import com.inin.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 6/4/16.
 */
@Service
public class TaskService {

    @Autowired
    private TaskDAOImpl taskDAO;

    @Autowired
    private CommentDAO commentDAO;

    /**
     * create method to create Task
     * where the DTO object sent is mapped with the actual DB model and sent to DAO for further processing.
     * @param taskDTO - object that is sent from the requester.
     * @throws IllegalArgumentException if data is not passed correctly
     * @throws OnCreateException if failed to create a new resource
     */
    public int createTask(TaskDTO taskDTO){
        if (!Util.checkObjectNull(taskDTO))
            throw new IllegalArgumentException("Please check the data you have sent.");

        if (!Util.checkValidString(taskDTO.getDescription()) || !Util.checkValidString(taskDTO.getStatus()) ||
                !Util.checkValidInteger(taskDTO.getAssigned_to()) || !Util.checkValidInteger(taskDTO.getAssignee())){
            throw new IllegalArgumentException("Please check the data sent through params.Are all required key present ?");
        }

        Task t = new Task(taskDTO.getDescription(), taskDTO.getStatus(), taskDTO.getAssignee(), taskDTO.getAssigned_to(), Util.parseToLocalDate(taskDTO.getDueDate()));
        int id = taskDAO.insert(t);
        if (!Util.checkValidInteger(id)){
            throw new OnCreateException("Operation failed");
        }
        return id;
    }

    /**
     * adds comment to the task, but before adding TaskId is set here
     * @param comment - comment object passed by the user
     * @param taskId - task Id passed
     * @throws IllegalArgumentException if the Comment object from request is null and if the task Id is 0 or less than 0
     */
    public void addComment(Comment comment, int taskId){
        if (!Util.checkObjectNull(comment))
            throw new IllegalArgumentException("Please check the date you have sent.");

        if (taskId <= 0)
            throw new IllegalArgumentException("Task id cannot be less than or equal to 0.");

        try {
            comment.setTaskId(taskId);
            comment.setCreated(LocalDateTime.now());
            commentDAO.insert(comment);
        }
        catch (DataAccessException e){
            throw new IllegalArgumentException("Something went wrong. Try again or Please check the input.");
        }

    }


    /**
     *
     * @param taskDTO - Request body sent by user to update Status or Postpone date
     * @param userId - userId passed in header
     * @param taskId - task id  @return - Task object
     * @throws IllegalArgumentException on data if not sent correctly or invalid
     * @throws UserNotAuthorizedException - if passed the incorrect userId whose is not assigned to the task
     */
    public Task updateTask(TaskDTO taskDTO, int taskId, int userId) {

        if (!Util.checkValidInteger(taskId) && !Util.checkValidInteger(userId))
            throw new IllegalArgumentException("User id Or Task Id should be a numeric value greater than 0");

        Task task;
        try{
            task = taskDAO.findById(taskId);
            if (userId != task.getAssignedTo() && userId != task.getAssignee()){
                throw new UserNotAuthorizedException("User not permitted.!!");
            }
        }catch (DataAccessException e ){
            throw new IllegalArgumentException("Task Id is invalid");
        }

        boolean isUpdate = false;
        if (Util.checkValidString(taskDTO.getStatus())){
            isUpdate = true;
            task.setStatus(taskDTO.getStatus());
        }

        if (Util.checkValidString(taskDTO.getDueDate())){
            try {
                task.setDueDate(Util.parseToLocalDate(taskDTO.getDueDate()));
                isUpdate = true;
            }
            catch (DateTimeParseException e){
                throw new IllegalArgumentException("Please enter a valid date time format");
            }

        }

        if (isUpdate){
            try {
                Task t = taskDAO.update(task, taskId);
                return t;
            }
            catch (DataIntegrityViolationException e){
                throw new IllegalArgumentException("Something went wrong. Try again.");
            }

        }
        return null;
    }

    /**
     *
     * @param status - String status sent
     * @param userId - integer userId from the header
     * @return - List of Task Object
     */
    public List<Task> getAllDraftTasks(String status, int userId){
        return taskDAO.findAllByDraft(status, userId);
    }

    /**
     * authorize and validate request to fetch all comments
     * @param userId - user id sent from the header
     * @param id -task Id
     * @return List of Comment for that task
     * @throws IllegalArgumentException on invalid / incorrect data
     * @throws UserNotAuthorizedException on invalid user id passed
     */
    public List<Comment> getComments( int id, int userId) {
        if (!Util.checkValidInteger(id) && !Util.checkValidInteger(userId))
            throw new IllegalArgumentException("User id Or Task Id should be a numeric value greater than 0");

        try{
            Task task = taskDAO.findById(id);

            if (userId != task.getAssignedTo() && userId != task.getAssignee()){
                throw new UserNotAuthorizedException("User not permitted.!!");
            }
        }catch (DataAccessException e ){
            throw new IllegalArgumentException("Task Id is invalid");
        }

        return taskDAO.getAllComments(id);
    }
}
