package com.example.dao;

import com.example.model.Message;

/**
 * Created by root on 13/5/16.
 */
public interface MessageDao {
    void insert(Message message);
    void findAll(int queueId);
    void deleteById(int queueId, int messageId);
}
