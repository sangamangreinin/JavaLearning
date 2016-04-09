package com.inin.dao;

import com.inin.domain.Comment;
import com.inin.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Created by evansbelly on 7/4/16.
 */
@Repository
public class CommentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * insert comment for a given task
     * @param comment
     * @param taskId
     * @return comment id
     */
    public void insert(Comment comment, int taskId) {
        jdbcTemplate.update("insert into comment(comment, taskId, commentBy, created, modified) values (?,?,?,?,?)",
                comment.getComment(), taskId, comment.getCommentBy(), comment.getCreated(), comment.getModified());
    }
}
