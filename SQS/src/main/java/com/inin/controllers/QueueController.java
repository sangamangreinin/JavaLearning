package com.inin.controllers;

import com.inin.controllers.dto.DeleteQueueRequest;
import com.inin.controllers.dto.QueueRequest;
import com.inin.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by root on 26/4/16.
 */
@RestController
public class QueueController {

    @Autowired
    QueueService queueService;

    @RequestMapping(method = RequestMethod.POST, path = "/sqs/queues", consumes = "application/json")
    public ResponseEntity createQueue(@RequestBody QueueRequest queueRequest){
        queueService.createQueue(queueRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/sqs/queues")
    public ResponseEntity deleteQueue(@RequestBody DeleteQueueRequest deleteQueueRequest){
        queueService.deleteQueue(deleteQueueRequest);
        return new ResponseEntity(HttpStatus.OK);
    }
}
