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
    public Task createTask(Task task){

        taskComponent.validateTask(task);
        SystemUser systemUser = getTaskUser(task.getAssigner().getId());

        if(!taskComponent.validateTaskUser(systemUser, task.getAssigner()))
            throw new InvalidInputException("Task Assigner information does not match with the record!");

        int taskId = taskRepository.add(task);
        task.setId(taskId);

        return task;
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
    public int addComment(Comment comment, int tid) {
        Task task = get(tid);
        if(task == null){
            throw new InvalidInputException("Invalid task given");
        }

        taskComponent.validateComments(comment);

        // only Assigner or Assignee can do the comments
/*        if((taskComponent.validateTaskUser(task.getAssigner(), comment.getCommenter()))
                || (taskComponent.validateTaskUser(task.getAssignee(), comment.getCommenter()))){

        }else {
            throw new InvalidInputException("Unauthorised user");
        }*/

        return taskRepository.saveComments(comment, tid);

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
    public int updateTask(int tid, int userId, Task task){

        taskComponent.validateTask(task);
        SystemUser user = userService.get(userId);
        Task updatingTask = get(tid);


        // things that can update by Assigner.
        if(taskComponent.validateTaskUser(updatingTask.getAssigner(), user)){

            if(task.getCurrentStatus() != null){

                // update assign date.
                if(updatingTask.getCurrentStatus() == Status.CREATED && task.getCurrentStatus() == Status.ASSIGNED){

                    updatingTask.setAssingDate(LocalDateTime.now());
                }

                updatingTask.setCurrentStatus(task.getCurrentStatus());
            }

            if(task.getAssignee() != null){

                updatingTask.setAssignee(task.getAssignee());
            }

            if(task.getDescription() != null) {

                updatingTask.setDescription(task.getDescription());
            }

            return taskRepository.update(tid, updatingTask);
        }


        // fields that can update by assignee.
        if(taskComponent.validateTaskUser(updatingTask.getAssignee(), user)){

            if(task.getDueDate() != null) {
                updatingTask.setDueDate(task.getDueDate());
            }

            return taskRepository.update(tid, updatingTask);
        }

        throw new InvalidInputException("Unauthorised Access!");

    }

}
