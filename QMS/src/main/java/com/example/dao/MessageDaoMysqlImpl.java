package com.example.dao;

import com.example.model.Message;
import org.springframework.stereotype.Repository;
/**
 * Created by root on 13/5/16.
 */
@Repository
public class MessageDaoMysqlImpl implements MessageDao{
    public void insert(Message message) {
        System.out.println("in insert of message");
    }

    public void findAll(int queueId){
       System.out.println("get list of unprocessed mesasges from queue");
    }
    public void deleteById(int queueId, int messageId){
        System.out.println("message deleted from queue");
    }
}
