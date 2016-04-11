package com.barca.dao;

import com.barca.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by root on 11/4/16.
 */
@Repository
public class CommentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @param taskId
     * @param comment
     */
    public void addComment(long taskId, Comment comment) {
        jdbcTemplate.update("insert into comment(userId,taskId,comment,created) values (?,?,?,?)", comment.getUserId(), taskId, comment.getComment(), LocalDateTime.now());
    }

    /**
     * @param taskId
     * @return List Of Comments
     */
    public List<Comment> getComments(long taskId) {
        return jdbcTemplate.query("SELECT * FROM comment WHERE taskId = ?", new Object[]{taskId}, (resultSet, rowNum) -> {
            return new Comment(resultSet.getString("comment"),
                    LocalDateTime.parse(resultSet.getString("created"), DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s.S")),
                    resultSet.getLong("userID"),
                    resultSet.getLong("taskId"));
        });
    }

}
