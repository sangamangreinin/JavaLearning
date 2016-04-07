package com.inin.taskmanager.service;

import com.inin.taskmanager.controller.dto.TaskQueryRequest;
import com.inin.taskmanager.domain.Comment;
import com.inin.taskmanager.domain.Task;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by virendra on 7/4/16.
 * CommentService Interface
 */
@Service
public interface CommentService {

    /**
     * saves the comment
     * @param comment Comment object
     * @return id of the newly generated comment
     * @throws IllegalAccessException if comment is not supplied
     */
    Long saveComment(Comment comment) throws IllegalAccessException;

    /**
     * finds the comment object
     * @param commentId comment id
     * @return Comment Object
     */
    Comment findComment(String commentId);

    /**
     * get comments of the task
     * @param task Task object
     * @return List of comment object
     */
    List<Comment> getComments(Task task);
}
