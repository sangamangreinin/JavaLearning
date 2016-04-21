package com.inin.dao;

import com.inin.model.Comment;

import java.util.List;

/**
 * Created by root on 18/4/16.
 */
public interface CommentDao {

    int insert(int taskId, Comment comment);

    List<Comment> findAll(int taskId);
}
