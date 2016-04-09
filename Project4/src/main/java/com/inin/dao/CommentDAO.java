package com.inin.dao;

import com.inin.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by root on 7/4/16.
 */
@Repository
public class CommentDAO {

    private final String TABLE = "tm_comment";

    @Autowired
    private JdbcTemplate template;

    /**
     * adds a comment on the Task
     * @param comment - comment object to be added
     */
    public void insert(Comment comment) {
        template.update("INSERT INTO "+TABLE+ "(comment, taskId, userId, created) VALUES (?, ?, ?, ?)",
                comment.getComment(),
                comment.getTaskId(),
                comment.getUserId(),
                comment.getCreated());
    }
}
