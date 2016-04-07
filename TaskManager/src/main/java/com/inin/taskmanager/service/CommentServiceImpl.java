package com.inin.taskmanager.service;

import com.inin.taskmanager.domain.Comment;
import com.inin.taskmanager.domain.Task;
import com.inin.taskmanager.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by virendra on 7/4/16.
 * CommentServiceImpl class. Implementation class for the CommentService Interface
 */
@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Long saveComment(Comment comment) throws IllegalAccessException {
        if(comment.getComment() == null){
            throw new IllegalAccessException("comment is required");
        }
        if(comment.getCommentBy() == null){
            throw new IllegalAccessException("user who made comment is required");
        }
        return commentRepository.save(comment);
    }

    @Override
    public Comment findComment(String commentId){
        return commentRepository.find(commentId);
    }

    @Override
    public List<Comment> getComments(Task task) {
        return task.getComments();
    }

}
