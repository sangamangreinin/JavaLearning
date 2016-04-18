package com.inin.dao;

import com.inin.domain.Comment;

import java.util.List;

/**
 * Created by root on 18/4/16.
 */
public interface CommentDao {

    void insert(int taskId, Comment comment);

    List<Comment> findAll(int taskId);
}
