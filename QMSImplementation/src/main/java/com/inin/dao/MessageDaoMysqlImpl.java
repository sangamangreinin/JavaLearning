package com.inin.dao;

import com.inin.model.Message;
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
 * Created by root on 13/5/16.
 */
@Repository
public class MessageDaoMysqlImpl implements MessageDao{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Insert a message for a queue in db
     * @param message message to be entered
     * @return id of newly created message
     */
    public int insert(Message message) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("message",            message.getMessage())
                .addValue("queueId",            message.getQueueId())
                .addValue("isProcessed",        message.isProcessed())
                .addValue("createdDateTime",    message.getCreatedDateTime());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update("Insert into messages (message, queueId, isProcessed, createdDateTime)"
                        + "VALUES(:message, :queueId, :isProcessed, :createdDateTime)",
                parameters, keyHolder, new String[]{"id"});

        int newMesageId =  keyHolder.getKey().intValue();
        return newMesageId;
    }

    /**
     * Get list of messages for a particular queue
     * @param queueId id of queue in int
     * @return list of messages
     */
    public List<Message> findAll(int queueId){
        String sql = "Select id, message, queueId, isProcessed, createdDateTime from messages where queueId = ? and isProcessed = 0";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Message.class), queueId);
    }

    /**
     * Remove the message from queue, Once consumer finish processing it
     * @param messageId message id in int
     */
    public void deleteById(int messageId){
        jdbcTemplate.update("UPDATE messages SET isProcessed = ?, consumedDateTime = ? WHERE  id = ? ", true, LocalDateTime.now(), messageId);
    }
}