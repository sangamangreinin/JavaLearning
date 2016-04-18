package com.inin.dao;

import com.inin.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @param taskId
     * @param comment
     */
    public void insert(int taskId, Comment comment){
        jdbcTemplate.update("Insert into comments (comment, commentedBy, taskId, commentDateTime) values(?,?,?,?)" ,
                comment.getDescription(), comment.getCommentedBy(), taskId, LocalDateTime.now());
    }

    /**
     * Get list of comments
     * @param taskId
     * @return the list of comments on a particular task
     */
    public List<Comment> findAll(int taskId){
        return jdbcTemplate.query("Select * from comments where taskId = ?", new Object[] {taskId}, (resultSet, rownum) -> {
            return new Comment(
                    resultSet.getInt("id"),
                    resultSet.getString("comment"),
                    resultSet.getInt("commentedBy"),
                    resultSet.getTimestamp("commentDateTime").toLocalDateTime()
            );
        });
    }
}
