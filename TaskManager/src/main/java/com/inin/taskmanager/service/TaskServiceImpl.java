package com.inin.taskmanager.service;

import com.inin.taskmanager.constant.TaskStatus;
import com.inin.taskmanager.controller.dto.TaskQueryRequest;
import com.inin.taskmanager.domain.Comment;
import com.inin.taskmanager.domain.Task;
import com.inin.taskmanager.domain.User;
import com.inin.taskmanager.exception.RecordNotCreatedException;
import com.inin.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by virendra on 5/4/16.
 * TaskServiceImpl class. Implementation class for service TaskService
 */
@Service
public class TaskServiceImpl implements TaskService {
    /**
     * taskRepository property stores TaskRepository object instance .
     */
    @Autowired
    private TaskRepository taskRepository;
    /**
     * userRepository property stores UserRepository object instance .
     */
    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    /**
     * default constructor for ticket service
     */
    public TaskServiceImpl() {

    }


    /**
     * gets the task object
     * @param taskId String task id
     * @return Task object
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public Task getTask(String taskId) {
        return taskRepository.find(taskId);
    }

    /**
     * creates the new task in the application
     * @param task Task object
     * @return Task object
     * @throws IllegalAccessException
     */
    @Override
    public Long createTask(Task task) throws IllegalAccessException {

        //validate creator
        task.setCreatedBy(modifyUser(task.getCreatedBy()));

        // set end date
        if (!task.getStatus().equals(TaskStatus.DRAFT))
            task.setAssignedTo(modifyUser(task.getAssignedTo()));

        //check for comments
        List<Comment> comments = task.getComments();

        if (comments != null){
            task.setComments(modifyCommentObjects(task, comments));
        }
        return taskRepository.save(task);
    }

    /**
     * modifies the comment object sent by the caller
     * @param task Task object
     * @param comments List of comments object
     * @return List of modified comments
     * @throws IllegalAccessException when comment is not added
     */
    private List<Comment> modifyCommentObjects( Task task, List<Comment> comments)
            throws IllegalAccessException {

        List<Comment> returnList = new ArrayList<>();

        if (!comments.isEmpty()){

            for (int i = 0 ; i<comments.size();i++){
                Comment tempObj = comments.get(i);
                tempObj.setCommentBy(task.getCreatedBy());
                Long id;

                if (tempObj == null){
                    throw new IllegalArgumentException("comment details cannot be blank. " +
                            "Please refer to documentation for proper format.");
                }else {
                    id = commentService.saveComment(tempObj);
                    returnList.add(i,commentService.findComment(id.toString()));
                }
            }
        }
        return returnList;
    }

    /**
     * modifies the user object sent by the caller
     * @param object User object
     * @return Modified User object
     * @throws IllegalAccessException when name of the user is not supplied by the caller
     */
    private User modifyUser(User object)
            throws IllegalAccessException {

        Long id;
        if (object == null){
            throw new IllegalArgumentException("user entry is required. " +
                    "Please refer to documentation for proper format.");
        }else {
            try{
                User obj = userService.search(object.getName());
                return obj;
            }catch (DataAccessException e){

            }
            id = userService.createUser(object);
        }

        if (id == 0 )
            throw new RecordNotCreatedException("Something went wrong!! Cannot create task");
        else {
            return userService.find(id.toString());
        }

    }

    /**
     * gets the task list present in the application
     * @return List of task objects
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public List<Task> getTasks(){
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getTasksForUser(String userId) {
        return taskRepository.search(new TaskQueryRequest.Builder().withCreatorId(userId).build());
    }

    @Override
    public List<Comment> searchComments(TaskQueryRequest request) {
        Task task  = taskRepository.find(request.getTaskId().toString());
        return commentService.getComments(task);
    }

    @Override
    public Long addComment(Long taskId, Comment comment) throws IllegalAccessException {
        Task task = taskRepository.find(taskId.toString());
        comment.setCommentBy(task.getCreatedBy());
        Long id = commentService.saveComment(comment);
        Iterator itr = task.getComments().iterator();
        List<Comment> comments = new ArrayList<>();
        while (itr.hasNext()){
            comments.add((Comment)itr.next());
        }
        comments.add(commentService.findComment(id.toString()));

        taskRepository.updateCommentIds(task, taskRepository.getCommentIds(comments));
        return id;
    }

    /**
     * retrieves the comment form the task
     * @param id task id for which comments need to be retrieved
     * @return List of comments
     * @throws IOException if
     * @throws ClassNotFoundException
     *//*
    public List<Comment> getComments(String id ) throws IOException, ClassNotFoundException {
        Task task = taskRepository.find(id);
        if (task == null)
            return null;
        return taskRepository.getComments(id);
    }

    public List<Task> query(TaskQueryRequest request) throws IOException, ClassNotFoundException {
        return taskRepository.searchComments(request);
    }
    */
}
