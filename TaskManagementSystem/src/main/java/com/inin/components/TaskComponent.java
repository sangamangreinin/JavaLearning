package com.inin.components;

import com.inin.domain.Comment;
import com.inin.domain.SystemUser;
import com.inin.domain.Task;
import com.inin.exceptions.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Deepak on 5/4/16.
 * This represents component class for task in system.
 */

@Component
public class TaskComponent extends BaseComponent{

    @Autowired
    UserComponent userComponent;
    /**
     * This is responsible for validating the task object to be stored.
     * @param task is task object to be stored.
     * */
    public void validateTask(Task task){

        validateNull(task.getTitle(), "title");
        validateNull(task.getAssigner(), "assigner");
        userComponent.validateUser(task.getAssigner());
    }


    /**
     * This is resoponsible to validate the comment object given on specific task
     * @param comment is comment object stored on task.
     * */
    public void validateComments(Comment comment){
        validateNull(comment.getMessage(), "message");
        validateNull(comment.getCommenter(), "commenter");

    }


    /**
     * This is responsible for validate user infomation passed with the task object.
     * @param systemUser is user information stored with the id which passed task object
     * @param taskUser is actual user information passed with task object. */
    public boolean validateTaskUser(SystemUser systemUser, SystemUser taskUser){
        if(systemUser == null)
            throw new InvalidInputException("Invalid user information");

        return userComponent.checkSameUsers(systemUser, taskUser);
    }




}
