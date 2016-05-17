package com.example.controllers;

import com.example.controllers.dto.MessageRequest;
import com.example.controllers.dto.QueueRequest;
import com.example.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * Created by root on 12/5/16.
 */

@RestController
@RequestMapping(path = "api/queues", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class QueueController {

    @Autowired
    QueueService queueService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public void createQueue(@RequestBody QueueRequest queueRequest){
        System.out.println("in controller");
        queueService.createQueue(queueRequest);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{queueId}/messages", consumes = "application/json")
    public void addMessageToQueue(@RequestBody MessageRequest messageRequest, @PathVariable int queueId){
        System.out.println("in message controller");
        queueService.addMessageToQueue(queueId, messageRequest);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{queueId}/messages", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void getListOfMessages(@PathVariable int queueId){
        queueService.getListOfMessages(queueId);
    }


    @RequestMapping(method = RequestMethod.PUT, path = "/{queueId}/messages/{messageId}", consumes = "application/json")
    public void removeMessageFromQueue(@PathVariable int queueId, int messageId){
        System.out.println("in message controller");
        queueService.removeMessageFromQueue(queueId, messageId);
    }
}
