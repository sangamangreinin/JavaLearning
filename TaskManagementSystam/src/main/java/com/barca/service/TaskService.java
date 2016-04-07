package com.barca.service;

import com.barca.dao.TaskDao;
import com.barca.model.Comment;
import com.barca.model.Task;
import com.barca.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Created by root on 7/4/16.
 */
@Service
public class TaskService {
    
    @Autowired
    private TaskDao taskDao;

    public long createTask(long id, Task task) {
        validationTask(task);
        return taskDao.insert(id,task);
    }

    private void validationTask(Task task) {
    }


    public ResponseEntity addComment(long taskId, Comment comment) {
        validationComment(comment);
        taskDao.addComment(taskId,comment);
        return new ResponseEntity(HttpStatus.CREATED);
    }


    private void validationComment(Comment comment) {
    }

    public Task getTask(long id) {
       return taskDao.getTask(id);
    }
}
