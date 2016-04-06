package com.inin.service;

import com.inin.components.TaskComponent;
import com.inin.domain.Comment;
import com.inin.domain.Status;
import com.inin.domain.SystemUser;
import com.inin.domain.Task;
import com.inin.exceptions.InvalidInputException;
import com.inin.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deepak on 2/4/16.
 * This service class represents task service of system.
 */

@Service
public class TaskService {

    @Autowired
    private SystemUserService userService;
    @Autowired
    private TaskComponent taskComponent;
    @Autowired
    private TaskRepository taskRepository;

    List<Comment> commentList = new ArrayList<>();


    /**
     * This service method to create task in system
     * @param task is object passed to store in system.
     * */
    public void createTask(Task task){

        taskComponent.validateTask(task);
        SystemUser systemUser = getTaskUser(task.getAssigner().getId());

        if(!taskComponent.validateTaskUser(systemUser, task.getAssigner()))
            throw new InvalidInputException("User information does not match with the record!");

        taskRepository.add(task);
    }


    /**
     * This is service get method to get the list of task stroed in system.`
     * */
    public List<Task> getAll(){

        return taskRepository.getAllTask();
    }


    /**
     * This is service method to get the particular task on the basis of id provided.
     * @param taskId is id of task to get.
     * */
    public Task get(int taskId){
        return taskRepository.getTask(taskId);
    }


    /**
     * This service method to add comments on task on the basis of task id provided..
     * @param comment is comment object to be stored on the task.
     * @param tid is unique id of task in system, on which comment has to made.
     * */
    public Task addComment(Comment comment, int tid) {
        Task task = get(tid);
        if(task == null){
            throw new InvalidInputException("Invalid task given");
        }

        taskComponent.validateComments(comment);

        // only Assigner or Assignee can do the comments
        if((taskComponent.validateTaskUser(task.getAssigner(), comment.getCommenter()))
                || (taskComponent.validateTaskUser(task.getAssignee(), comment.getCommenter()))){

            return taskRepository.saveComments(comment, tid);

        }else {

            throw new InvalidInputException("Unauthorised user");
        }

    }

    /**
     * This is utility method to get the task user of system.
     * @param id is unique id of system user in system. */
    private SystemUser getTaskUser(int id){
        return userService.get(id);
    }


    /**
     * This service method to change the status of task on the basis of task id privided.
     * @param tid is unique task id.
     * @param userId id user id who is trying ot update the status.
     * @param task is updated task object */
    public Task updateTask(int tid, int userId, Task task){

        taskComponent.validateTask(task);
        SystemUser user = userService.get(userId);
        Task updatingTask = get(tid);

        // updating status allowed to Assigner only.
        if(task.getCurrentStatus() != null && updatingTask.getCurrentStatus() != task.getCurrentStatus()){
            if(taskComponent.validateTaskUser(updatingTask.getAssigner(), user)){

                // update assign date.
                if(updatingTask.getCurrentStatus() == Status.CREATED && task.getCurrentStatus() == Status.ASSIGNED){
                    updatingTask.setAssingDate(LocalDateTime.now());
                }

                updatingTask.setCurrentStatus(task.getCurrentStatus());

            }
            else{
                throw new InvalidInputException("Invalid Assigner");
            }
        }

        // update due date of task
        if(task.getDueDate() != null && updatingTask.getDueDate() != task.getDueDate()){
            if(taskComponent.validateTaskUser(updatingTask.getAssignee(), user)){

                updatingTask.setDueDate(task.getDueDate());
            }
            else{
                System.out.println();
                throw new InvalidInputException("Invalid Assignee");
            }
        }

        updatingTask.setAssignee(task.getAssignee());
        updatingTask.setDescription(task.getDescription());

        return updatingTask;
    }

}
