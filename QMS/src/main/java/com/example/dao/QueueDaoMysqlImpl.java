package com.example.dao;

import com.example.model.Queue;
import org.springframework.stereotype.Repository;

/**
 * Created by root on 12/5/16.
 */
@Repository
public class QueueDaoMysqlImpl implements QueueDao {

    public void insert(Queue queue) {
        System.out.println("in insert of queue");
    }
}
