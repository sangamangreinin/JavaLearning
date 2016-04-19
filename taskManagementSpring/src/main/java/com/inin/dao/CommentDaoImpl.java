package com.inin.dao;

import com.inin.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class consist of all DB related operations for comment
 */
@Repository
public class CommentDaoImpl implements CommentDao{
    /**
     * get bean of jdbcTemplate for DB connection
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * insert a comment for a task in db
     * @param taskId id of task in int
     * @param comment comment object
     */
    public void insert(int taskId, Comment comment){
        jdbcTemplate.update("Insert into comments (comment, commentedBy, taskId, commentDateTime) values(?,?,?,?)" ,
                comment.getDescription(), comment.getCommentedBy(), taskId, LocalDateTime.now());
    }

    /**
     * Get list of comments
     * @param taskId id of task in int
     * @return the list of comments on a particular task
     */
    public List<Comment> findAll(int taskId){
        String sql = "Select * from comments where taskId = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Comment.class), taskId);
    }
}
