package com.inin.service;

import com.inin.controllers.dto.ProducerRequest;
import com.inin.dao.QueueDao;
import com.inin.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by root on 26/4/16.
 */
@Service
public class ProducerService {
    @Autowired
    QueueDao queueDao;

    public void sendMessage(ProducerRequest producerRequest){
        System.out.println("producer request service");
        Message message = new Message(producerRequest.emailId);
        queueDao.sendMessage(producerRequest.queueUrl, message.getEmailId());
    }
}
