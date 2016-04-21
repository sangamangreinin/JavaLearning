package com.inin.dao;

import com.inin.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class consist of all DB related operations for comment
 */
@Repository
public class CommentDaoMySqlImpl implements CommentDao{
    /**
     * get bean of jdbcTemplate for DB connection
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * insert a comment for a task in db
     * @param taskId id of task in int
     * @param comment comment object
     */
    public int insert(int taskId, Comment comment){
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("comment",             comment.getDescription())
                .addValue("commentedBy",         comment.getCommentedBy())
                .addValue("taskId",              taskId)
                .addValue("commentDateTime",     LocalDateTime.now());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update("Insert into comments (comment, commentedBy, taskId, commentDateTime)"
                        + "VALUES(:comment, :commentedBy, :taskId, :commentDateTime)",
                parameters, keyHolder, new String[]{"id"});

        int newCommentId =  keyHolder.getKey().intValue();
        return newCommentId;
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
