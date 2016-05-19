package com.inin.dao;

import com.inin.model.Message;

import java.util.List;

/**
 * Created by root on 13/5/16.
 */
public interface MessageDao {
    int insert(Message message);
    List<Message> findAll(int queueId);
    void deleteById(int messageId);
}
