package com.inin.controllers;

import com.inin.controllers.dto.QueueRequest;
import com.inin.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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


}
