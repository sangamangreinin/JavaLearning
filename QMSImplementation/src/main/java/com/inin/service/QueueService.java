package com.inin.service;

import com.inin.controllers.dto.MessageRequest;
import com.inin.controllers.dto.QueueRequest;
import com.inin.dao.MessageDao;
import com.inin.dao.QueueDao;
import com.inin.exceptions.QueueDoesNotExistException;
import com.inin.model.Message;
import com.inin.model.Queue;
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
    MessageDao messageDao;

    @Autowired
    UserService userService;

    /**
     * create a new queue
     * @param queueRequest
     * @return an id of newly created queue
     * @throws IllegalArgumentException if the queue request object passed was null or invalid argument passed
     */
    public int createQueue(QueueRequest queueRequest){
        validateQueueRequest(queueRequest);
        userService.isProducerExist(queueRequest.producerId);
        userService.isConsumerExist(queueRequest.consumerId);

        return queueDao.insert(new Queue(queueRequest.name, queueRequest.producerId, queueRequest.consumerId));
    }

    /**
     * add message to queue
     * @param queueId id of queue in int
     * @param messageRequest message request object
     * @return an id of newly added message
     * @throws IllegalArgumentException if the message request object passed was null or invalid argument passed
     * @throws QueueDoesNotExistException if the queue id does not exist
     */
    public int addMessageToQueue(int queueId, MessageRequest messageRequest){
        validateMessageRequest(messageRequest);
        isQueueExist(queueId);
        return messageDao.insert(new Message(messageRequest.message, false, queueId));
    }

    /**
     * get list of messages from a particular queue
     * @param queueId id of queue in int
     * @return the list of messages
     * @throws QueueDoesNotExistException if the specified queue id does not exist
     */
    public List<Message> getListOfMessages(int queueId){
        isQueueExist(queueId);
        return messageDao.findAll(queueId);
    }

    /**
     * remove message from queue
     * @param queueId id of queue in int
     * @param messageId id of message in int
     * @throws QueueDoesNotExistException id of the queue exist or not
     */
    public void removeMessageFromQueue(int queueId, int messageId){
        isQueueExist(queueId);
        messageDao.deleteById(messageId);
    }

    /**
     * validate queue attributes
     * @param queueRequest Queue request object
     */
    private void validateQueueRequest(QueueRequest queueRequest){
        if(queueRequest == null){
            throw new IllegalArgumentException("Queue Request object passed was null.");
        }
        if(queueRequest.name == ""){
            throw new IllegalArgumentException("Queue name cannot be empty.");
        }
        if(queueRequest.consumerId <= 0){
            throw new IllegalArgumentException("Consumer id should be greater than zero.");
        }
        if(queueRequest.producerId <= 0){
            throw new IllegalArgumentException("Producer id should be greater than zero.");
        }
        if(queueRequest.producerId ==  queueRequest.consumerId){
            throw new IllegalArgumentException("Producer and consumer should be different.");
        }
    }

    /**
     * validate message attributes
     * @param messageRequest Message request object
     */
    private void validateMessageRequest(MessageRequest messageRequest){
        if(messageRequest == null){
            throw new IllegalArgumentException("Message Request object passed was null.");
        }
        if(messageRequest.message == ""){
            throw new IllegalArgumentException("Message cannot be empty");
        }
    }

    /**
     * Check if queue exist or not
     * @param queueId id of queue in int
     * @return true if queue exist else false
     */
    public boolean isQueueExist(int queueId){
        if(!(queueDao.isQueueExist(queueId))){
            throw new QueueDoesNotExistException("Queue with id " +queueId+" does not exist.");
        }
        return true;
    }
}
