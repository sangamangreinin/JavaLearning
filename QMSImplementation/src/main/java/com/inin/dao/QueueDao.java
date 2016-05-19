package com.inin.dao;

import com.inin.model.Queue;

/**
 * Created by root on 12/5/16.
 */
public interface QueueDao {
    int insert(Queue queue);
    boolean isQueueExist(int id);
}
