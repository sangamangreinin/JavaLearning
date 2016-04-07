package com.inin.taskmanager.repository;

import com.inin.taskmanager.domain.Comment;
import com.inin.taskmanager.domain.Task;
import com.inin.taskmanager.domain.User;
import com.inin.taskmanager.exception.RecordNotFoundException;
import com.inin.taskmanager.service.UserService;
import com.inin.taskmanager.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by virendra on 5/4/16.
 * CommentRepository class.
 */
@Repository
public class CommentRepository {

    @Autowired
    JdbcTemplate template;

    @Autowired
    private UserService userRepository;


    /**
     * saves the Comment object
     * @param obj Comment object representation
     * @return id of the newly created Comment
     */
    public Long save(Comment obj) {
        String query = String.format("INSERT INTO %s (comment, comment_by, created, modified) " +
                "VALUES (?,?,?,?)", Comment.TABLE_NAME);


        String created = LocalDateTime.now().format(Util.DB_DATE_FORMAT);
        String modified = LocalDateTime.now().format(Util.DB_DATE_FORMAT);

        String comment = obj.getComment();


        Long commentBy = obj.getCommentBy().getUserId();

        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(
                con -> {
                    PreparedStatement pst =
                            con.prepareStatement(query, new String[] {"id"});
                    pst.setString(1, comment);
                    pst.setLong(2, commentBy);
                    pst.setString(3, created);
                    pst.setString(4, modified);

                    return pst;
                },
                keyHolder);

        Long id = keyHolder.getKey().longValue();

        return id;

    }

    /**
     * finds the comment object based on the id supplied by the caller
     * @param commentId String comment Id
     * @return Comment Object
     */
    public Comment find(String commentId) {

        String query = String.format("SELECT " +
                "id, comment,comment_by, created, modified FROM %s WHERE id=?", Comment.TABLE_NAME);


        List<Comment> comments = template.query(query, new Object[]{commentId},
                (entry, i) -> {

            User commentByObj = null;

            try {
                commentByObj = userRepository.find(entry.getString("comment_by"));
            } catch (DataAccessException e) {
                throw new RecordNotFoundException("error in fetching the comment entry");

            }
            LocalDateTime created = LocalDateTime.parse(entry.getString("created"), Util.DB_DATE_FORMAT);
            LocalDateTime modified = LocalDateTime.parse(entry.getString("modified"),  Util.DB_DATE_FORMAT);

            return
                    new Comment
                            .Builder(entry.getString("comment"), commentByObj)
                            .setCommentId(entry.getLong("id"))
                            .setCreatedDate(created)
                            .setModifiedDate(modified)
                            .create();
        });

        Optional<Comment> comment =
                comments.stream().filter(i -> i.getCommentId() == Long.parseLong(commentId)).findAny();

        if (comment.isPresent()){
            return comment.get();
        }else
            throw new RecordNotFoundException("Could not find the comment");
    }

    /**
     * fetched list of comment for the ids supplied
     * @param ids id list
     * @return List of Comment objects
     */
    public List<Comment> fetchComments(String ids) {
        String[] tmp = ids.split(",");

        List<Comment> comments = new ArrayList<>();

        for (int i = 0 ; i<tmp.length;i++){
            try{
                Comment tmpObj = find(tmp[i]);
                if(tmpObj != null)
                    comments.add(tmpObj);
            }catch (DataAccessException e){

            }
        }

        return Collections.unmodifiableList(comments);

    }
}
