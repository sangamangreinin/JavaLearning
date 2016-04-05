package com.inin.taskmanager.repository;

import com.inin.taskmanager.domain.Comment;
import com.inin.taskmanager.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by virendra on 5/4/16.
 * CommentRepository class.
 */
@Repository
public class CommentRepository {

    @Autowired
    JdbcTemplate template;

    private String getUniqueId(){
        return Util.getMasterCommentId();
    }

    public Comment save(Comment obj) {
        String query = String.format("INSERT INTO %s (commentid, comment, comment_by, created,modified) " +
                "VALUES (?,?,?,?,?)", Comment.TABLE_NAME);

        String commentId = getUniqueId();

        int added = template.update(
                query,
                commentId,
                obj.getComment(),
                obj.getCommentBy().getUserId(),
                obj.getCreatedDate(),
                obj.getModifiedDate()
        );
        Comment commentObj = null;
        if (added == 1){
            commentObj = find(commentId);
        }
        return commentObj;
    }

    private Comment find(String commentId) {
        return null;
    }
}
