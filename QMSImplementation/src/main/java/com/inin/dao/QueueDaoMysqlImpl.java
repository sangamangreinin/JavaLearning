package com.inin.dao;

import com.inin.model.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 * Created by root on 12/5/16.
 */
@Repository
public class QueueDaoMysqlImpl implements QueueDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Insert a queue
     * @param queue queue object
     * @return the id of newly created queue
     */
    public int insert(Queue queue) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name",            queue.getName())
                .addValue("producerId",      queue.getProducerId())
                .addValue("consumerId",      queue.getConsumerId())
                .addValue("createdDate",     queue.getCreatedDate());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update("Insert into queues (name, producerId, consumerId, createdDate)"
                        + "VALUES(:name, :producerId, :consumerId, :createdDate)",
                parameters, keyHolder, new String[]{"id"});

        int newQueueId =  keyHolder.getKey().intValue();
        return newQueueId;
    }

    /**
     * check if queue exist
     * @param id queue id in int
     * @return true if exist else false
     */
    public boolean isQueueExist(int id) {
        int count = jdbcTemplate.queryForObject("Select count(*) from queues where id = ?", new Object[]{id}, Integer.class);
        if(count > 0){
            return true;
        }
        return false;
    }
}
