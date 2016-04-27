package com.inin.service;

import com.inin.controllers.dto.ConsumerRequest;
import com.inin.dao.QueueDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by root on 27/4/16.
 */
@Service
public class ConsumerService {
    @Autowired
    QueueDao queueDao;

    public void receiveMessage(ConsumerRequest consumerRequest){
        queueDao.receiveMessage(consumerRequest.queueUrl);
    }
}
