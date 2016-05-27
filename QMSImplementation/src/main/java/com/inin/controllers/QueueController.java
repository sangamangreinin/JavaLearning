package com.inin.controllers;

import com.inin.Error;
import com.inin.controllers.dto.MessageRequest;
import com.inin.controllers.dto.QueueRequest;
import com.inin.exceptions.QueueDoesNotExistException;
import com.inin.exceptions.UserDoesNotExistException;
import com.inin.model.Message;
import com.inin.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


/**
 * Created by root on 12/5/16.
 */

@RestController
@RequestMapping(path = "api/queues", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class QueueController {

    /**
     * get bean/object for queue service
     */
    @Autowired
    QueueService queueService;

    /**
     * Common exception for queue controller, The queue does not exist exception will get caught here
     * @param e exception object
     * @return Error response code for QueueDoesNotExistException
     */
    @ExceptionHandler(QueueDoesNotExistException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    public Error handleQueueDoesNotExistException(Exception e){
        return new Error(101, e.getMessage());
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    public Error handleUserDoesNotExistException(Exception e){
        return new Error(105, e.getMessage());
    }

    /**
     * Create Queue for producer and consumer,
     * and returns the HTTP 201[CREATED] along with a LocationHeader containing the locations of newly created queue
     * @param queueRequest queueRequest object
     * @return the HTTP 201[CREATED] along with a LocationHeader containing the locations of newly created queue
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity createQueue(@RequestBody QueueRequest queueRequest, UriComponentsBuilder ucBuilder){
        int id =  queueService.createQueue(queueRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/queues/{id}").buildAndExpand(id).toUri());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    /**
     * add message to a queue
     * @param messageRequest message request object
     * @param queueId id of queue in int
     * @param ucBuilder
     * @return the httpstatus OK[200] if message added successfully
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/{queueId}/messages", consumes = "application/json")
    public ResponseEntity addMessageToQueue(@RequestBody MessageRequest messageRequest, @PathVariable int queueId, UriComponentsBuilder ucBuilder){
        int id =  queueService.addMessageToQueue(queueId, messageRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/messages/{id}").buildAndExpand(id).toUri());
        return new ResponseEntity(headers, HttpStatus.OK);
    }

    /**
     * get the list of messages from a particular queue
     * @param queueId id of queue in int
     * @return the list of messages
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{queueId}/messages")
    public ResponseEntity getListOfMessages(@PathVariable int queueId){
        List<Message> messages = queueService.getListOfMessages(queueId);
        if(messages.isEmpty()){
            return new ResponseEntity("No messages found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(messages, HttpStatus.OK);
    }

    /**
     * remove message from queue
     * @param queueId id of queue in int
     * @param messageId id of message in int
     * @return the Httpstatus OK [200] if the message was removed from queue successfully
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "/{queueId}/messages/{messageId}")
    public ResponseEntity removeMessageFromQueue(@PathVariable int queueId, @PathVariable  int messageId){
        queueService.removeMessageFromQueue(queueId, messageId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
