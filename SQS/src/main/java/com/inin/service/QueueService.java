package com.inin.service;
import com.amazonaws.services.sqs.model.QueueDoesNotExistException;
import com.inin.controllers.dto.DeleteQueueRequest;
import com.inin.controllers.dto.ProducerRequest;
import com.inin.controllers.dto.QueueRequest;
import com.inin.dao.QueueDao;
import com.inin.model.Message;
import com.inin.model.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Created by root on 26/4/16.
 */
@Service
public class QueueService {

    @Autowired
    QueueDao queueDao;

    public void createQueue(QueueRequest queueRequest){
        Queue queue = new Queue(queueRequest.queueName);
        queueDao.createQueue(queue.getQueueName());
    }

    public boolean isQueueExist(String queueUrl){
        if(!(queueDao.isQueueExist(queueUrl))){
            throw new QueueDoesNotExistException("Queue "+queueUrl+"does not exist!!");
        }
        return true;
    }

    public void deleteQueue(DeleteQueueRequest deleteQueueRequest){
        queueDao.deleteQueue(deleteQueueRequest.queueUrl);
    }


}
