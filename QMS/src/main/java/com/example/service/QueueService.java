package com.example.service;

import com.example.controllers.dto.MessageRequest;
import com.example.controllers.dto.QueueRequest;
import com.example.dao.MessageDao;
import com.example.dao.QueueDao;
import com.example.model.Message;
import com.example.model.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by root on 12/5/16.
 */
@Service
public class QueueService {

    @Autowired
    QueueDao queueDao;

    @Autowired
    private MessageDao messageDao;

    public void createQueue(QueueRequest queueRequest){
        System.out.println("in service");
        queueDao.insert(new Queue("first queue", 1, 2));
    }

    public void addMessageToQueue(int queueId, MessageRequest messageRequest){
        System.out.println("in message service");
        messageDao.insert(new Message("test message", false, queueId));
    }
    public void getListOfMessages(int queueId){
        messageDao.findAll(queueId);
    }

    public void removeMessageFromQueue(int queueId, int messageId){
        messageDao.deleteById(queueId, messageId );
    }
}
